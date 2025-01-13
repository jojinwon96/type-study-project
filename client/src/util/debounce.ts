export const debounce = (callback: Function, delay: number) => {
    let debounceTimeout: number | null = null;

    return () => {
        if (debounceTimeout) {
            clearTimeout(debounceTimeout);
        }
        debounceTimeout = window.setTimeout(() => {  // window.setTimeout 사용
            callback();
        }, delay);
    };
};
