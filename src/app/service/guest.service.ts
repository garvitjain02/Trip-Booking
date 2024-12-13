import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class GuestService {
    constructor(private httpClient: HttpClient) { }

    private addGuestApi = "http://localhost:8000/guest/add";
    public addGuest (guest: any) : Observable<any> {
        return this.httpClient.post(this.addGuestApi, guest);
    }

    private addGuestIDApi = "http://localhost:8000/guest/add/idProof";
    public addGuestID (formData: FormData, id: any) {
        return this.httpClient.post(this.addGuestIDApi + '/' + id, formData);
    }

    private bookingHasGuestApi = "http://localhost:8000/booking/guests/";
    public bookingHasGuest(bid:any, gid: any): Observable<any> {
        const httpOptions = {
            headers : new HttpHeaders({
                Authorization: 'Bearer '+ localStorage.getItem('token')
             })
        };
        return this.httpClient.post(this.bookingHasGuestApi + bid + '/' + gid, null, httpOptions);
    }
}