import axios from 'axios';

export default function api(type, url, data, option) {
  url = url + (url.indexOf('?') < 0 ? '?' : '&') + param(data);
  return new Promise((resolve, reject) => {
    axios({
      method: type,
      url: url,
      data: data
    }).then(res => {
      resolve(res.data);
    }).catch((error) => {
      reject(error);
    });
  });
}

function param(data) {
  let url = '';
  for (let k in data) {
    let value = data[k] !== undefined ? data[k] : '';
    url += '&' + k + '=' + encodeURIComponent(value);
  }
  return url ? url.substring(1) : '';
}
