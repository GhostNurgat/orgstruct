const api = {
    base: "http://localhost:8230/api/v1/orgStructures",
    company: "/getCompany/"
};

let info = document.querySelector('.info');
let structure = document.querySelector('#structure');

let entityLegal = 'БСЗ';

let store = {};

const fetchCompany = async () => {
    const response = await fetch(`${api.base}${api.company}${entityLegal}`)
        .then(res => res.json());
    const {companyName, entity} = response;
    store = {
        companyName: name,
        entity: legal
    };

    renderComponent();
    return response;
}

const renderComponent = () => {
    structure.innerHTML = getContent();
}

const getContent = () => {
    const {name, legal} = store;

    return `<div class="col-sm-4">
        <div class="structure-container">${name}</div>
    </div>`;
}