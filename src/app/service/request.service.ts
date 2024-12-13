import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private getHotelRequestsApi= 'http://localhost:8082/hotel-requests/all';
  private postHotelRequestsApi= 'http://localhost:8082/hotel-requests/vendor-hotels-add';
  private getFLightRequestApi= 'http://localhost:8082/flight-requests/all';
  private postFlightRequestApi='http://localhost:8082/flight-requests/approveHotelRequest';
  private getVendorRequestApi= 'http://localhost:8082/user/vendors-request';

  constructor(private httpClient: HttpClient) { }

  getHotelRequest(): Observable<any> {
    return this.httpClient.get(this.getHotelRequestsApi);
  }

  deleteHotelRequest(id:any): Observable<any> {
    return this.httpClient.delete("http://localhost:8082/hotel-requests/delete/"+id);
  }

  postHotelRequest(hotel:any): Observable<any>{
    return this.httpClient.post(this.postHotelRequestsApi, hotel);
  }

  getFlightRequest():Observable<any>{
    return this.httpClient.get(this.getFLightRequestApi)
  }

  deleteFlightRequest(id:any):Observable<any>{
    return this.httpClient.delete("http://localhost:8082/flight-requests/deleteById/"+id)
  }

  postFlightRequest(flight:any):Observable<any>{
    return this.httpClient.post(this.postFlightRequestApi,flight)
  }

  getVendorRequest():Observable<any>{
    return this.httpClient.get(this.getVendorRequestApi);
  }

  deleteUserById(id:any):Observable<any>{
    return this.httpClient.delete('http://localhost:8082/user/deleteById/'+id)
  }

  postApproveVendors(id:any):Observable<any>{
    return this.httpClient.post(`http://localhost:8082/user/approveVendorId?id=`+id, null);
  }

}
