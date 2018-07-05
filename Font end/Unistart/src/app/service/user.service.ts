import { Injectable } from '@angular/core';
import { User } from '../shared/user.model';
import { ApiService } from './../app.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private apiService: ApiService) { }
  createUser(user: User) {
    return new Promise((resolve, reject) => {
        this.apiService.post('model.users/', user ).then(res => {
            resolve(res.text());
        }).catch(err => {
            reject(err);
        });
    });
}
}
