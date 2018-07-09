import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { Http } from '@angular/http';

@Injectable()
export class UniversitytService {
    constructor(private apiService: ApiService, private http: Http) { }
    getList() {
        return new Promise((resolve, reject) => {

            this.apiService.get('Product').then(res => {
                resolve(res.json());
            }).catch(err => {
                reject(err);
            });
        });
    }

}
