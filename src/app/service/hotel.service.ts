import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class HotelService {

    constructor(private httpClient: HttpClient) { }

    private getHotelsApi = "http://localhost:8000/api/hotel/search";
    public getHotels(location: any) : Observable<any> {
        return this.httpClient.get(this.getHotelsApi + "?location=" + location);
    }

    private getHotelByIdApi = "http://localhost:8000/api/hotel/details";
    public getHotelById (id: any): Observable<any> {
        return this.httpClient.get(this.getHotelByIdApi + "/" + id);
    }

    private getHotelByVendorApi = "http://localhost:8000/api/hotel/vendor";
    public getHotelByVendor(): Observable<any> {
        return this.httpClient.get(this.getHotelByVendorApi + '?username=' + localStorage.getItem('username'));
    }

    private getHotelByHotelIdApi = "http://localhost:8000/api/hotel";
    public getHotelByHotelId (id: any) : Observable<any> {
        return this.httpClient.get(this.getHotelByHotelIdApi + "/" + id);
    }

    private updateHotelApi = "http://localhost:8000/api/hotel/update";
    public updateHotel (hotel: any) : Observable<any> {
        return this.httpClient.put(this.updateHotelApi, hotel);
    }
}