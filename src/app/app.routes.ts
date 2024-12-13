import { Routes } from '@angular/router';
import { SignUpPageComponent } from './pages/sign-up-page/sign-up-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { HotelSearchPageComponent } from './pages/hotel-search-page/hotel-search-page.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { HotelDetailPageComponent } from './pages/hotel-detail-page/hotel-detail-page.component';
import { AddGuestPageComponent } from './pages/add-guest-page/add-guest-page.component';
import { VendorDashboardPageComponent } from './pages/vendor-dashboard-page/vendor-dashboard-page.component';
import { BookingRequestsPageComponent } from './pages/booking-requests-page/booking-requests-page.component';
import { VendorPageComponent } from './pages/vendor-page/vendor-page.component';
import { AuthGuardService } from './service/auth-guard.service';
import { HotelEditComponent } from './pages/hotel-edit/hotel-edit.component';

export const routes: Routes = [
    {
        path : "hotel", component : LandingPageComponent
    },
    {
        path : "sign-up", component : SignUpPageComponent
    },
    {
        path : "login", component : LoginPageComponent
    },
    {
        path : "hotel/search", component : HotelSearchPageComponent
    },
    {
        path : "hotel/detail/:id", component : HotelDetailPageComponent
    },
    {
        path : "hotel/reservation/confirm", component : AddGuestPageComponent, canActivate: [AuthGuardService]
    },
    {
        path: 'vendor', component: VendorPageComponent, canActivate: [AuthGuardService], children : [
            {
                path : "", component : VendorDashboardPageComponent
            },
            {
                path : "booking-requests", component : BookingRequestsPageComponent
            },
            {
                path : "hotel/edit/:id", component : HotelEditComponent
            }
        ]
    },
    {
        path : "**", component: PageNotFoundComponent
    }
];
