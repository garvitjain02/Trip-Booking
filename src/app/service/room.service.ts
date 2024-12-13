import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class RoomService {
    constructor (private httpClient: HttpClient) { }

    private getRoomsByHotelApi = "http://localhost:8000/api/hotel/rooms";
    public getRoomsByHotel (id: any) : Observable<any> {
        return this.httpClient.get(this.getRoomsByHotelApi + "/" + id);
    }
}