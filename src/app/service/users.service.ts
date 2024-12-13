import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getAllCustomersApi='http://localhost:8082/user/allCustomers';
  private getAllVendorsApi='http://localhost:8082/user/allVendors';

  constructor(private httpClient: HttpClient) { }

  getAllCustomers(): Observable<any>{
    return this.httpClient.get(this.getAllCustomersApi);
  }

  getAllVendors(): Observable<any>{
    return this.httpClient.get(this.getAllVendorsApi);
  }

  getByUsername(usernname: any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/user/byUsername/"+usernname)
  }

  getAllLogs():Observable<any>{
    return this.httpClient.get("http://localhost:8082/log/all")
  }

  getPagedLogs(paged:any, size:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/log/pagedall?page="+paged+"&size="+size)
  }

  getLogsByUserId(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/log/getUserLogsByUserId/"+id)
  }

  uploadImage(formData:any, id:any):Observable<any>{
    return this.httpClient.post("http://localhost:8082/user/addCertificate/"+id,formData);
  }

  viewCertificate(id:any):Observable<any>{
    return this.httpClient.get("http://localhost:8082/user/getCertificateByVendorId/"+id)
  }
}
