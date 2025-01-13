export const deepClone = <T>(obj: T): T => {
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }

    if (Array.isArray(obj)) {
        const arrCopy: any[] = [];
        for (let i = 0; i < obj.length; i++) {
            arrCopy[i] = deepClone(obj[i]);  // 배열 요소 재귀 복사
        }
        return arrCopy as T;  // 배열 복사본 반환
    } else {
        const objCopy: Record<string, any> = {};
        for (const key in obj) {
            if (obj.hasOwnProperty(key)) {
                objCopy[key] = deepClone(obj[key]);  // 객체 속성 재귀 복사
            }
        }
        return objCopy as T;  // 객체 복사본 반환
    }
};
