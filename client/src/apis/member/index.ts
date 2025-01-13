import type ApiResponse from '../common/api.response'
import type { Member } from './dto/member'
import { useRouter } from 'vue-router'

// 로컬 주소
const DOMAIN = 'http://localhost:8080'

// server 요청 url
const GET_MEMBERS_URL = () => `${DOMAIN}/members`
const POST_MEMBER_URL = () => `${DOMAIN}/members`
const GET_MEMBER_BY_ID_URL = (sequence: number) => `${DOMAIN}/members/${sequence}`
const PATCH_MEMBER_URL = (sequence: number) => `${DOMAIN}/members/${sequence}`
const DELETE_MEMBER_URL = () => `${DOMAIN}/members`

export const getFetch = async (url: string) => {
  await fetch(url).then((response) => response.json())
}

export const sendRequest = async (url: string, method: string, requestBody: unknown) => {
  const requestOptions: RequestInit = {
    method,
    headers: {
      'Content-type': 'application/json; charset=UTF-8',
    },
  }

  if (method !== 'GET' && requestBody) {
    requestOptions.body = JSON.stringify(requestBody)
  }

  const response = await fetch(url, requestOptions)
  return response.json()
}

export const useMember = () => {
  const router = useRouter()

  /** 사용자 목록  */
  let members: Member[] = []

  const getMembers = (): Member[] => {
    return members
  }

  const setMembers = (data: Member[]) => {
    members = data
  }

  const clearMembers = () => {
    members = []
  }

  /** 사용자 전체 조회 */
  const getMembersApi = async () => {
    await sendRequest(GET_MEMBERS_URL(), 'GET', null)
      .then((responseData: ApiResponse<Member[]>) => {
        const { code, data } = responseData
        if (code === 'SU') {
          setMembers(data)
        }
      })
      .catch((error) => {
        console.error('getMemberApi 요청 실패 : ', error)
      })
  }

  /** 단일 사용자 추가 */
  const createMemberApi = async (request: Member) => {
    await sendRequest(POST_MEMBER_URL(), 'POST', request)
      .then((responseData: ApiResponse<Member | string[]>) => {
        const { code, data } = responseData
        if (code === 'BR') {
          alert(data)
        }
        if (code === 'SU') {
          router.push({ path: '/member' })
        }
      })
      .catch((error) => {
        console.error('createMemberApi 요청 실패 : ', error)
      })
  }

  /** 단일 사용자 조회 */
  let member: Member = { id: '', name: '', age: 0, createDate: '' }

  const getMember = (): Member => {
    return member
  }

  const setMember = (data: Member) => {
    member = data
  }

  const getMemberApi = async (sequence: number) => {
    await fetch(GET_MEMBER_BY_ID_URL(sequence))
      .then((response) => response.json())
      .then((responseData: ApiResponse<Member>) => {
        const { code, data, message } = responseData
        if (code === 'SU') {
          setMember(data)
        }
        if (code === 'NF') {
          alert(message)
        }
      })
      .catch((error) => {
        console.error('getMemberApi 요청 실패 : ', error)
      })
  }

  /** 단일 사용자 수정 */
  const patchMemberApi = async (sequecne: number, requestBody: Member) => {
    await fetch(PATCH_MEMBER_URL(sequecne), {
      method: 'PATCH',
      body: JSON.stringify(requestBody),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    })
      .then((response) => response.json())
      .then((responseData: ApiResponse<Member>) => {
        const { code } = responseData
        if (code === 'SU') {
          router.push({ name: 'detailMemberView', params: { sequecne } })
        }
      })
      .catch((error) => {
        console.error('patchMemberApi 요청 실패 : ', error)
      })
  }

  /** 다중 사용자 삭제  */
  const deleteMemberApi = async (requestBody: Member[]) => {
    await fetch(DELETE_MEMBER_URL(), {
      method: 'DELETE',
      body: JSON.stringify(requestBody),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    })
      .then((response) => response.json())
      .then((responseData: ApiResponse<string[]>) => {
        const { code } = responseData
        if (code === 'SU') {
          router.push({ name: 'memberMainView' })
        }
      })
      .catch((error) => {
        console.error('patchMemberApi 요청 실패 : ', error)
      })
  }

  return {
    getMembers,
    getMembersApi,
    clearMembers,
    createMemberApi,
    getMember,
    getMemberApi,
    patchMemberApi,
    deleteMemberApi,
  }
}
