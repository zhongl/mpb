// import Plotly from 'plotly.js-dist-min'

// var values = [
//     ['Salaries', 'Office', 'Merchandise', 'Legal', '<b>TOTAL</b>'],
//     [1200000, 20000, 80000, 2000, 12120000],
//     [1300000, 20000, 70000, 2000, 130902000],
//     [1300000, 20000, 120000, 2000, 131222000],
//     [1400000, 20000, 90000, 2000, 14102000]]

// var data = [{
//     type: 'table',
//     header: {
//         values: [["<b>EXPENSES</b>"], ["<b>Q1</b>"],
//         ["<b>Q2</b>"], ["<b>Q3</b>"], ["<b>Q4</b>"]],
//         align: "center",
//         line: { width: 1, color: 'black' },
//         fill: { color: "grey" },
//         font: { family: "Arial", size: 12, color: "white" }
//     },
//     cells: {
//         values: values,
//         align: "center",
//         line: { color: "black", width: 1 },
//         font: { family: "Arial", size: 11, color: ["black"] }
//     }
// }]

// Plotly.newPlot("dt", data);

const container = document.querySelector("#dt");

function element(value) {
    const e = document.createElement("pre");
    e.innerText = JSON.stringify(value, null, 2);
    return e;
}

chrome.storage.session.get(null, v => {
    container.appendChild(element(v));
});
chrome.storage.session.onChanged.addListener((changes, area) => {
    container.appendChild(element(changes));
});

