import axios from "axios"
import {auth} from "../store/auth.js";

const send = async ({method='', path='', data={}, accessToken=''} = {}) => {
    const commonUrl = 'http://localhost:8080/api'
    const url = commonUrl + path
    let accessTokenValue;
    const unsubscribe = auth.subscribe(value => {
        accessTokenValue = value.accessToken;
    });
    unsubscribe();

    const headers = {
        "content-type": "application/json;charset=UTF-8",
        "accept": "application/json,",
        'Authorization': accessTokenValue ==='' ?  '' : `Bearer ${accessTokenValue}`
    };

    const options = {
        method,
        url,
        headers,
        data,
        withCredentials: true,
    }

    try {
        const response = await axios(options);
        return response.data
    }
    catch(error) {
        if(error.response.status === 401) {
            alert("로그인하세요!")
        } else if(error.response.status === 404) {
            alert("그런 거 없습니다")
        } else {
            alert("내부 에러 발생")
        }
    }
}

const getApi = ({path='', access_token=''} = {}) => {
    return send({method: 'GET', path: path, access_token: access_token})
}

const putApi = ({path='', data={}, access_token=''} = {}) => {
    return send({method: 'PUT', path: path, data: data, access_token: access_token})
}

const patchApi = ({path='', data={}, access_token=''} = {}) => {
    return send({method: 'PATCH', path: path, data: data, access_token: access_token})
}

const postApi = ({path='', data={}, access_token=''} = {}) => {
    return send({method: 'POST', path: path, data: data, access_token: access_token})
}

const delApi = ({path='', data={}, access_token=''} = {}) => {
    return send({method:'DELETE', path: path, data: data, access_token: access_token})
}

export {
    getApi,
    putApi,
    patchApi,
    postApi,
    delApi,
}