import {get} from "./utilities/fetchApi.m.js";
import {create_sample_table} from "./utilities/table.m.js";
import {paginator} from "./utilities/paginator.m.js";

async function callOptaSolver(fileName) {
    document.getElementById('result').innerHTML = `<img src="giphy.gif">`;

    let result = await get(`/solveRostering?fileName=${fileName}`).then(response => {
        return response.text() || '{}';
    });
    let data = JSON.parse(result);
    document.getElementById('result').innerHTML = '';
    document.getElementById("table_box_native").innerHTML = '';
    document.getElementById("index_native").innerHTML = '';

    let shiftAssignmentList = data.shiftAssignmentList;
    let hardScore = data.score.hardScore;
    let softScore = data.score.softScore;

    document.getElementById('result').innerHTML = `Hard score is ${hardScore}, Soft Score is ${softScore}`;

    let preparedTableContent = prepareTableContent(shiftAssignmentList);
    renderTableContent(preparedTableContent);
}

function prepareTableContent(data) {
    let tableData = {
        'head': ['Shift Type', 'Contract', 'Shift', 'Shift Date', 'Nurse'],
        'body': [],
        'foot': ['Shift Type', 'Contract', 'Shift', 'Shift Date', 'Nurse']
    };

    for (let i = 0; i < data.length; i++) {
        let shiftResult = data[i];
        let item = [];
        item.push(shiftResult['shiftType']['label']);
        item.push(shiftResult['contract']['description']);
        item.push(shiftResult['shift']['label']);
        item.push(shiftResult['shiftDate']['label']);
        item.push(shiftResult['employee']['label']);
        tableData.body.push(item);
    }
    return tableData;
}

function renderTableContent(data) {
    create_sample_table(document.getElementById("table_box_native"), true, true, true, data);
    var box = paginator({
        table: document.getElementById("table_box_native").getElementsByTagName("table")[0],
        box: document.getElementById("index_native"),
        active_class: "color_page",
        rows_per_page: 15
    });
    return box;
}

async function testUI() {
    let result = await get('/sampleResultForCSS.JSON').then(response => {
        return response.text() || '{}';
    });

    let data = JSON.parse(result);
    let preparedTableContent = prepareTableContent(data);
    renderTableContent(preparedTableContent);
}

document.querySelectorAll("a").forEach(item => {
    item.addEventListener('click', e => {
        let fileName = e.target.id;
        callOptaSolver(fileName);
    });
});

// testUI();
// callOptaSolver();


