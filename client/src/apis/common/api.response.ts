export default interface ApiResponse<T>{
    timestamp?: string;
    txId?: string;
    code?: string;
    message?: string;
    data?:T;
}