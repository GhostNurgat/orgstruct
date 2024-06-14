const api = {
    base: "http://localhost:8230/api/v1/orgStructures",
    companies: "/getCompanies",
    locations: "/getLocations",
    subdivisions: "/getSubdivisions",
    departments: "/getDepartments",
    groups: "/getGroups",
    workers: "/getWorkers"
};

let menu = document.querySelector('.nav-menu');
let sidebar = document.querySelector('.sidebar');
let close = document.querySelector('.arrow');

let info = document.querySelector('.nav-location');

let container = document.querySelector('.container');

let footerContainer = document.querySelector('.footer-container');
let backButton = document.createElement('button');
backButton.classList.add('back-button');
backButton.textContent = 'Назад';

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
let elDepartments = [];
let elGroups = [];
let elWorkers = [];

backButton.addEventListener('click', (e) => {
    let elements = document.querySelectorAll('.element-container');
    e.preventDefault();
    switch (backButton.id) {
        case '':
            elements.forEach(el => container.removeChild(el));
            fetchCompanies();
            break;
        case 'locations':
            elements.forEach(el => container.removeChild(el));
            renderElements(elLocations);
            info.textContent = localStorage.getItem('locations');
            fetchLocations();
            backButton.id = null;
            break;
        case 'subdivisions':
            elements.forEach(el => container.removeChild(el));
            renderElements(elSubdivisions);
            info.textContent = localStorage.getItem('subdivisions');
            backButton.id = 'locations';
            break;
        case 'departments':
            elements.forEach(el => container.removeChild(el));
            renderElements(elDepartments);
            info.textContent = localStorage.getItem('departments');
            backButton.id = 'subdivisions';
            break;
        case 'groups':
            let workers = document.querySelector('.workers-box');
            container.removeChild(workers);
            renderElements(elGroups);
            info.textContent = localStorage.getItem('groups');
            backButton.id = 'departments';
            break;

    }
});

company.addEventListener('click', (e) => {
    e.preventDefault();
    container.removeChild(el);
    renderLocation();
    info.textContent = `Локация. ${storeCompany.companyName}`;
    footerContainer.appendChild(backButton);
    localStorage.setItem('locations', info.textContent);
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

            element.addEventListener('click', () => {
                fetchSubdivisions(id);
                info.textContent = `Подразделения. ${name}`;
                localStorage.setItem('subdivisions', info.textContent);
                backButton.id = '';
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

const renderSubdivisions = () => {
    let elements = document.querySelectorAll('.element-container');
    elements.forEach(el => container.removeChild(el));

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

            element.addEventListener('click', () => {
                fetchDepartments(subId);
                info.textContent = `Отдел. Подразделения "${subName}"`;
                backButton.id = 'locations';
                localStorage.setItem('departments', info.textContent);
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

    renderSubdivisions();
};

const addElementArray = (array, store) => {
    array.push(store);
};

const renderDepartments = () => {
    let elements = document.querySelectorAll('.element-container');
    elements.forEach(el => container.removeChild(el));

    let n = departments.length;
    if (departments.length === 9)
        n = departments.length / 3;
    else if (departments.length % 3 === 0)
        n = 3;
    else if (departments.length % 3 !== 0 && departments.length > 3)
        n = 3;

    while (true) {
        if (departments.length === 0) break;

        let elementContainer = document.createElement('div');
        elementContainer.className = 'element-container';

        let range = departments.splice(0, n);
        for (let i = 0; i < range.length; i++) {
            let element = document.createElement('div');
            let text = document.createElement('span');
            element.className = 'element';
            text.className = 'text-element';

            const {departmentId, departmentName} = range[i];
            let len = departmentName.length;
            text.textContent = departmentName || 'Группы';

            element.style.width = `${len + 20}vw`;
            element.style.borderColor = '#ecd400';
            element.id = departmentId;
            element.appendChild(text);

            element.addEventListener('click', () => {
                fetchGroups(departmentId);
                info.textContent = `Группы. Отдел "${departmentName}"`;
                backButton.id = 'subdivisions';
                localStorage.setItem('groups', info.textContent);
            });

            elDepartments.push(element);
            elementContainer.appendChild(element);
        }

        container.appendChild(elementContainer);

        if (departments.length < 3)
            n = departments.length;
        else if (departments.length % 3 === 0)
            n = 3;
        else n = 3;
    }
};

const fetchDepartments = async (id) => {
    const response = await fetch(`${api.base}${api.departments}`);
    let allDepatrments = await response.json();
    let temp = allDepatrments.filter(d => d.subdivisionResponse.subId === id);

    for (let i = 0; i < temp.length; i++) {
        const {departmentId, departmentName} = temp[i];
        let store = {
            departmentId,
            departmentName
        };
        addElementArray(departments, store);
    }

    renderDepartments();
};

const renderGroups = () => {
    let elements = document.querySelectorAll('.element-container');
    elements.forEach(el => container.removeChild(el));

    let n = groups.length;
    if (groups.length === 9)
        n = groups.length / 3;
    else if (groups.length % 3 === 0)
        n = 3;
    else if (groups.length % 3 !== 0 && groups.length > 3)
        n = 3;

    while (true) {
        if (groups.length === 0) break;

        let elementContainer = document.createElement('div');
        elementContainer.className = 'element-container';

        let range = groups.splice(0, n);
        for (let i = 0; i < range.length; i++) {
            let element = document.createElement('div');
            let text = document.createElement('span');
            element.className = 'element';
            text.className = 'text-element';

            const {groupId, groupName} = range[i];
            let len = groupName.length;
            text.textContent = groupName || 'Работники';

            element.style.width = `${len + 20}vw`;
            element.style.borderColor = '#8ce25f';
            element.id = groupId;
            element.appendChild(text);

            element.addEventListener('click', () => {
                fetchWorkers(groupId);
                if (workers === null) {
                    alert('Не найдины данные о работниках в в данной группе');
                    return;
                }
                info.textContent = `Работники. Группы "${groupName}"`;
                backButton.id = 'groups';
            });

            elGroups.push(element);
            elementContainer.appendChild(element);
        }

        container.appendChild(elementContainer);

        if (groups.length < 3)
            n = groups.length;
        else if (groups.length % 3 === 0)
            n = 3;
        else n = 3;
    }
};

const fetchGroups = async (id) => {
    const response = await fetch(`${api.base}${api.groups}`);
    let allGroups = await response.json();
    let temp = allGroups.filter(g => g.departmentResponse.departmentId === id);

    for (let i = 0; i < temp.length; i++) {
        const {groupId, groupName} = temp[i];
        let store = {
            groupId,
            groupName
        };
        addElementArray(groups, store);
    }

    renderGroups();
};

const renderWorkers = () => {
    let elements = document.querySelectorAll('.element-container');
    elements.forEach(el => container.removeChild(el));

    let workersBox = document.createElement('div');
    let workerContainer = document.createElement('div');
    workersBox.classList.add('workers-box');
    workerContainer.classList.add('worker-container');

    container.appendChild(workersBox);

    for (let i = 0; i < workers.length; i++) {
        let worker = document.createElement('div');
        let post = document.createElement('span');
        let line = document.createElement('div');
        let header = document.createElement('h6');
        let p = document.createElement('p');
        worker.classList.add('worker');
        post.classList.add('post', 'post-header');
        line.classList.add('header-line');

        post.textContent = worker[i].postResponse.name;

        header.textContent = 'ФИО';
        p.textContent = worker[i].fio;
        worker.append(header, p);

        let typeHeader = document.createElement('h6');
        let type = document.createElement('p');
        typeHeader.textContent = 'Тип работы';
        type.textContent = worker[i].typeWorkResponse.typeName;
        worker.append(typeHeader, type);

        workerContainer.append(post, line, worker);
    }

    workersBox.appendChild(workerContainer);
};

const fetchWorkers = async (id) => {
    const response = await fetch(`${api.base}${api.workers}`);
    let allWorkers = await response.json();
    let temp = allWorkers.filter(w => w.groupResponse.groupId === id);
    if (temp === null) return;

    for (let i = 0; i < temp.length; i++) {
        const {id, fio, postResponse, typeWorkResponse} = temp[i];
        let store = {
            id,
            fio,
            postResponse,
            typeWorkResponse
        };
        addElementArray(workers, store);
    }

    renderWorkers();
}

const renderElements = (elements) => {
    let n = elements.length;
    if (elements.length === 9)
        n = 3;
    else if (elements.length % 3 === 0)
        n = 3;
    else if (elements.length % 3 !== 0 && elements.length > 3)
        n = 3;

    while (elements.length !== 0) {
        let elementContainer = document.createElement('div');
        elementContainer.className = 'element-container';

        let range = elements.splice(0, n);
        for (let i = 0; i < range.length; i++) {
            elementContainer.appendChild(range[i]);
        }

        container.appendChild(elementContainer);

        if (elements.length < 3)
            n = elements.length;
        else if (elements.length % 3 === 0)
            n = 3;
        else n = 3;
    }
};