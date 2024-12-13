import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class BookingService {
    constructor (private httpClient: HttpClient) { }

    private createBookingApi = "http://localhost:8000/api/booking/add";
    public createBooking(booking: any): Observable<any> {
        let token = localStorage.getItem('token');
        const httpOptions = {
            headers : new HttpHeaders({
                Authorization : 'Bearer ' + token
            })
        };
        return this.httpClient.post(this.createBookingApi, booking, httpOptions);
    }

    private roomHasBookingApi = "http://localhost:8000/booking/guests/";
    public roomHasBooking(bid: any, rid: any): Observable<any> {
        return this.httpClient.post(this.roomHasBookingApi + bid + '/' + rid, null);
    }

    private bookingRequestsApi = "http://localhost:8000/api/booking/requests";
    public bookingRequests () : Observable<any> {
        return this.httpClient.get(this.bookingRequestsApi + "?username=" + localStorage.getItem('username'));
    }

    private updateApprovalStatusApi = "http://localhost:8000/api/approvalStatus/";
    public updateApprovalStatus(id:any, status: any): Observable<any> {
        
        return this.httpClient.put(this.updateApprovalStatusApi+id + "?status=" + status, null);
    }
}