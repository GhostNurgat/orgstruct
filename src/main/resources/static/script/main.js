const api = {
    base: "http://localhost:8230/api/v1/orgStructures",
    company: "/getCompany/"
};

let menu = document.querySelector('.nav-menu');
let sidebar = document.querySelector('.sidebar');
let close = document.querySelector('.close-container');

menu.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "320px";
});

close.addEventListener('click', (e) => {
    e.preventDefault();
    sidebar.style.width = "0";
});