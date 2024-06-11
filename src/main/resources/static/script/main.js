const api = {
    base: "http://localhost:8230/api/v1/orgStructures",
    companies: "/getCompanies",
    locations: "/getLocations",
    subdivisions: "/getSubdivisions"
};

let menu = document.querySelector('.nav-menu');
let sidebar = document.querySelector('.sidebar');
let close = document.querySelector('.close-container');

let info = document.querySelector('.nav-location');

let container = document.querySelector('.container');

let locations = [];
let subdivisions = [];
let departments = [];
let groups = [];
let workers = [];

menu.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "320px";
});

close.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "0";
});

let storeCompany = {};
let company = document.createElement('div');
let el = document.createElement('div');

const fetchCompanies = async () => {
    const response = await fetch(`${api.base}${api.companies}`);
    let arr = await response.json();

    const {entityLegal, companyName} = arr[0];
    storeCompany = {
        entityLegal,
        companyName
    };

    renderCompany();
}

const renderCompany = () => {
    container.appendChild(getCompanyComponent());
}

const getCompanyComponent = () => {
    el.classList.add('company-container');
    company.classList.add('company');

    let logo = document.createElement('span');
    logo.classList.add('logo');

    let img = document.createElement('img');
    img.src = '/logo/brusnika.jpg';
    img.alt = 'Лого компании Брусника';
    img.style.width = '50px';
    img.style.height = '50px';
    logo.appendChild(img);
    company.appendChild(logo);

    let line = document.createElement('span');
    line.classList.add("line");
    company.appendChild(line);

    let name = document.createElement("span");
    name.classList.add("name");
    name.textContent = `${storeCompany.companyName}`;
    company.appendChild(name);

    el.appendChild(company);
    return el;
}

fetchCompanies();

console.log(locations);

let storeLocation = {};

const fetchLocations = async () => {
    const response = await fetch(`${api.base}${api.locations}`);
    let allLocations = await response.json();
    let temp = allLocations.filter(loc => loc.companyResponse.entityLegal === storeCompany.entityLegal);

    for (let i = 0; i < temp.length; i++) {
        const {id, name} = temp[i];
        storeLocation = {
            id,
            name
        };
        addLocationArray();
    }
};

const addLocationArray = () => {
    locations.push(storeLocation);
};

fetchLocations();

let elLocations = [];
let elSubdivisions = [];

company.addEventListener('click', (e) => {
    e.preventDefault();
    container.removeChild(el);
    renderLocation();
    info.textContent = `Локация. ${storeCompany.companyName}`;
});

const renderLocation = () => {
    let n = 0;
    if (locations.length % 3 === 0) {
        n = locations.length / 3;
    } else {
        n = 3;
    }

    while (true) {
        if (locations.length === 0) break;

        let elementContainer = document.createElement('div');
        elementContainer.classList.add('element-container');

        let range = locations.splice(0, n);
        for (let i = 0; i < range.length; i++) {
            let element = document.createElement("div");
            let text = document.createElement("span");
            element.classList.add('element');
            text.classList.add('text-element');

            const {id, name} = range[i];
            let len = name.length;
            text.textContent = name;

            element.style.width = `${len + 20}vw`;
            element.style.borderColor = '#ee3d24';
            element.id = id;
            element.appendChild(text);

            element.addEventListener('click', (e) => {
                e.preventDefault();
                renderSubdivisions(id);
                info.textContent = `Подразделения. ${name}`;
            });

            elLocations.push(element);
            elementContainer.appendChild(element);
        }

        container.appendChild(elementContainer);

        if (locations.length % 3 === 0) {
            n = 3;
        } else {
            n = locations.length % 3;
        }
    }
};

const renderSubdivisions = (id) => {
    let elements = document.querySelectorAll('.element-container');
    elements.forEach(el => container.removeChild(el));

    fetchSubdivisions(id);

    let n = subdivisions.length;
    if (subdivisions.length === 9)
        n = subdivisions.length / 3;
    else if (subdivisions.length % 3 === 0)
        n = 3;
    else if (subdivisions.length % 3 !== 0 && subdivisions.length > 3)
        n = 3;

    while (true) {
        if (subdivisions.length === 0) break;

        let elementContainer = document.createElement('div');
        elementContainer.className = 'element-container';

        let range = subdivisions.splice(0, n);
        for (let i = 0; i < range.length; i++) {
            let element = document.createElement('div');
            let text = document.createElement('span');
            element.className = 'element';
            text.className = 'text-element';

            const {subId, subName} = range[i];
            let len = subName.length;
            text.textContent = subName == null ? 'Отдел' : subName;

            element.style.width = `${len + 20}vw`;
            element.style.borderColor = '#ef7924';
            element.id = subId;
            element.appendChild(text);

            element.addEventListener('click', (e) => {
                e.preventDefault();
            });

            elSubdivisions.push(element);
            elementContainer.appendChild(element);
        }

        container.appendChild(elementContainer);

        if (subdivisions.length < 3)
            n = subdivisions.length;
        else if (subdivisions.length % 3 === 0)
            n = 3;
        else n = 3;
    }
};

const fetchSubdivisions = async (id) => {
    const response = await fetch(`${api.base}${api.subdivisions}`);
    let allSubs = await response.json();
    let temp = allSubs.filter(sub => sub.locationResponse.id === id);

    for (let i = 0; i < temp.length; i++) {
        const {subId, subName} = temp[i];
        let store = {
            subId,
            subName
        };
        addElementArray(subdivisions, store);
    }
};

const addElementArray = (array, store) => {
    array.push(store);
};