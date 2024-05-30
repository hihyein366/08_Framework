/* 개인 API 인증키 */

const serviceKey = "Xxkh/M30jsXREPbJc66Sps73cpTG1RY7wl63KDZgYCjokfeNfVBJxiNfmN/ooytGYotlRVXTrrmTI9UM/6vVXg==";

//
const returnType = "JSON";
// const location = document.querySelector("#location");
const currentWeather = document.querySelector(".current-weather");


const getAirPollution = (sidoName) => {

    const requestUrl = 'http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty';
    // const sidoName = location;

    // 쿼리 스트링 생성 (URLSearchParams.toString())
    const searchParams = new URLSearchParams();
    searchParams.append("serviceKey", serviceKey);
    searchParams.append("returnType", returnType);
    searchParams.append('sidoName', sidoName);

    // 
    fetch(`${requestUrl}?${searchParams.toString()}`)
    .then(resp => resp.json())
    .then(result => {

    console.log(result);

})

.catch(e => console.log(e));

}