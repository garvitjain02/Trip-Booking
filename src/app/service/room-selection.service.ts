import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class RoomSelectionService {
    private selectedRoomsSource = new BehaviorSubject<any[]>([]);

  selectedRooms = this.selectedRoomsSource.asObservable();

  addRoomToSelection(room: any) {
    let currentRooms = this.selectedRoomsSource.value;
    currentRooms.push(room);
    this.selectedRoomsSource.next(currentRooms);
  }
}