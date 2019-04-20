export const req = (method, url, data) => {
    let requestBody = {
        method: method,
        body: undefined,
        headers: {
            'Accept': 'application/json'
        },
        credentials: 'same-origin'
    };

    if (data) {
        requestBody.body = JSON.stringify(data);
        requestBody.headers['Content-Type'] = 'application/json';
    }

    return fetch(url, requestBody);

};

export const get = (url) => req('GET', url, undefined);
export const post = (url, data) => req('POST', url, data);