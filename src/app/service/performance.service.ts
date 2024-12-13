import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  getAllFlightApi='http://localhost:8082/entity/getflight';
  getAllHotelApi='http://localhost:8082/entity/gethotel';
  getAllFlightBookingsApi='http://localhost:8082/flightbooking/all';

  constructor(private httpClient: HttpClient) { }

  getHotelBookingsByUsername(username:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/hotelbooking/getbyusername/"+username);
  }

  getFlightBookingsByUsername(username:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/flightbooking/getbyusername/"+username);
  }

  deleteUsersByUsername(username:any):Observable<any>{
    return this.httpClient.delete("http://localhost:8082/user/deleteuserbyusername/"+username);
  }

  getHotelsByVendorUsername(username:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/hotel-requests/getHotelsByVendor/"+username);
  }

  getFlightsByVendorUsername(username:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/flightbooking/getFlightsByVendor/"+username);
  }

  getAllHotel():Observable<any>{
    return this.httpClient.get(this.getAllHotelApi);
  }

  getAllFlight():Observable<any>{
    return this.httpClient.get(this.getAllFlightApi);
  }

  getAllFlightBookings():Observable<any>{
    return this.httpClient.get(this.getAllFlightBookingsApi);
  }

  getFlightById(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/flightbooking/getById/"+id);
  }

  getAllFlightBookingsByFlightId(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/flightbooking/getFlightBookingByFlightId/"+id);
  }

  deleteFlightById(id:any):Observable<any>{
    return this.httpClient.delete('http://localhost:8082/flightbooking/deleteFlightById/'+id);
  }

  getHotelById(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/hotelbooking/getHotelById/"+id)
  }

  getAllHotelBookingsByHotelId(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/hotelbooking/getHotelBookingByHotelId/"+id)
  }

  deleteHotelById(id:any):Observable<any>{
    return this.httpClient.delete("http://localhost:8082/hotelbooking/deleteHotelById/"+id)
  }

  getCustomerByUsername(username:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/user/byUsername/"+username)
  }

  getRatingsByHotelId(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/rating/getByHotelId/"+id)
  }

  getRatingsByFlightId(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/rating/getByFlightId/"+id)
  }

}
