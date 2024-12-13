import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class SearchService {
    private locationSource = new BehaviorSubject<string>('Delhi');
  private checkInDateSource = new BehaviorSubject<string>('');  
  private checkOutDateSource = new BehaviorSubject<string>('');  
  private guestsSource = new BehaviorSubject<number>(2);

  location = this.locationSource.asObservable();
  checkInDate = this.checkInDateSource.asObservable();
  checkOutDate = this.checkOutDateSource.asObservable();
  guests = this.guestsSource.asObservable();

  updateLocation(location: string) {
    this.locationSource.next(location);
  }

  updateCheckInDate(checkInDate: string) {
    this.checkInDateSource.next(checkInDate);
  }

  updateCheckOutDate(checkOutDate: string) {
    this.checkOutDateSource.next(checkOutDate);
  }

  updateGuests(guests: number) {
    this.guestsSource.next(guests);
  }
}