export class fetchData {
    constructor(base) {
        this.base = base;
    }

    async getCompanies(companies) {
        const response = await fetch(`${this.base}${companies}`)
            .then(res => res.json());
        return response.data;
    }
}