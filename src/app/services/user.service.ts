import { UpdateDetails } from './../models/update-details';
import { UserDetails } from './../models/user-details';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private getUrl: string = "http://localhost:7001/user";

  constructor(private _httpClient: HttpClient) { }

  getAllUserDetails(): Observable<any> {
    return this._httpClient.get<any>(`${this.getUrl}/record`);
  }

  getAllUserRole(): Observable<any> {
    return this._httpClient.get<any>(`${this.getUrl}/role`);
  }

  getManagerRecordById(id: number): Observable<any> {
    return this._httpClient.get<any>(`${this.getUrl}/${id}`).pipe(
      map(response => response)
    )
  }

  updateRecord(details:UpdateDetails,id: number) {
    return this._httpClient.put<UpdateDetails>(`${this.getUrl}/${id}`, details);
  }
}
