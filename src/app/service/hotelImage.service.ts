import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class HotelImageService {
    constructor (private httpClient: HttpClient) { }

    private getHotelImagesApi = "http://localhost:8000/api/hotel/images";
    public getHotelImages(id: any) : Observable<any> {
        return this.httpClient.get(this.getHotelImagesApi + "?hid=" + id);
    }
}