const api = {
    base: "http://localhost:8230/api/v1/orgStructures",
    companies: "/getCompanies"
};

let menu = document.querySelector('.nav-menu');
let sidebar = document.querySelector('.sidebar');
let close = document.querySelector('.close-container');

let container = document.querySelector('.container-fluid');

menu.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "320px";
});

close.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "0";
});

let storeCompany = {};

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
    container.innerHTML = getCompanyComponent();
}

const getCompanyComponent = () => {
    return `<div class="company-container">
        <div class="company">
            <span class="logo">
                <img src="/logo/brusnika.jpg" alt="лого Брусники" width="50" height="50">
            </span>
            <span class="line"></span>
            <span class="name">${storeCompany.companyName}</span>
        </div>
    </div>`
}

fetchCompanies();