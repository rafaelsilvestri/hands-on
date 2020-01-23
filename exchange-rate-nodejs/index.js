const fetch = require('node-fetch');

var url = 'https://economia.awesomeapi.com.br/json/list/USD-BRL/2';
var headers = {
    "Content-Type": "application/json"
}

const avg = (val1, val2) =>  (Number(val1) + Number(val2)) / 2


fetch(url, { method: 'GET', headers: headers })
.then((res) => res.json())
.then((json) => {
  const [ rateToday, rateYesterday ] = json;
  //console.log(rateToday);

  console.log(`
    Cotação USD - BRL
    
    Name: 
    High value: R$ ${rateToday.high}
    Low value: R$ ${rateToday.low}
    BID: R$ ${rateToday.bid}
    Day Average: R$ ${avg(rateToday.high, rateToday.low)}
    Last 2 days average: R$ ${(avg(rateToday.high, rateToday.low) 
        + avg(rateYesterday.high, rateYesterday.low)) / 2}
  `)
})


