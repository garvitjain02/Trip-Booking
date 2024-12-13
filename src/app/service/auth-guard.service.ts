import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, MaybeAsync, GuardResult } from "@angular/router";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
})

export class AuthGuardService {
    token: string | undefined | null;
    role: string | undefined | null;
    accessData=[{
        path: 'vendor',
        role:'HOTEL_VENDOR'
    },
    {
        path: 'hotel/reservation/confirm',
        role:'CUSTOMER' 
    }]
    
    constructor (private router: Router) {
        this.token = localStorage.getItem("token");
        this.role = localStorage.getItem("role");
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
        console.log('I am blocking everything');
        console.log('token: ' + this.token)
        console.log(route)
        console.log(state)

        if (this.token === null) {
            this.router.navigateByUrl("**");
            return false;
        }

        let pathVal = route.routeConfig?.path;

        let allowedObj = this.accessData.filter(e => e.path === pathVal)[0];

        if (allowedObj.role === this.role)
            return true;

        console.log("invalid role");
        this.router.navigateByUrl("**");

        return false;
    }
}