import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class ProfileService {
    constructor (private httpClient: HttpClient) { }

    private getUserDetailsApi = "http://localhost:8000/auth/user";
    getUserDetails() : Observable<any> {
        const httpOptions = {
            headers : new HttpHeaders({
                Authorization : 'Bearer ' + localStorage.getItem("token")
            })
        };

        return this.httpClient.get(this.getUserDetailsApi, httpOptions);
    }

    private updateUserApi = "http://localhost:8000/api/user/update";
    updateUser(user: any): Observable<any> {
        return this.httpClient.put(this.updateUserApi, user);
    }
}