import { Routes } from '@angular/router';
import { SignupComponent } from './page/signup/signup.component';
import { LoginComponent } from './page/login/login.component';
import { ExecutiveComponent } from './page/executive/executive.component';
import { AuthpageComponent } from './page/authpage/authpage.component';
import { ExecutiveLogtableComponent } from './component/forexecutive/executive-logtable/executive-logtable.component';
import { ExecutiveRequestsComponent } from './component/forexecutive/executive-requests/executive-requests.component';
import { ExecutivePerformanceComponent } from './component/forexecutive/executive-performance/executive-performance.component';
import { CustomerPerformanceComponent } from './component/forexecutive/customer-performance/customer-performance.component';
import { VendorPerformanceComponent } from './component/forexecutive/vendor-performance/vendor-performance.component';
import { EntityPerformanceComponent } from './component/forexecutive/entity-performance/entity-performance.component';
import { FlightRequestsComponent } from './component/forexecutive/flight-requests/flight-requests.component';
import { HotelRequestsComponent } from './component/forexecutive/hotel-requests/hotel-requests.component';
import { DetailedCustomerPerformanceComponent } from './component/forexecutive/detailed-customer-performance/detailed-customer-performance.component';
import { DetailedVendorPerformanceComponent } from './component/forexecutive/detailed-vendor-performance/detailed-vendor-performance.component';
import { DetailedHotelPerformanceComponent } from './component/forexecutive/detailed-hotel-performance/detailed-hotel-performance.component';
import { DetailedFlightPerformanceComponent } from './component/forexecutive/detailed-flight-performance/detailed-flight-performance.component';
import { PostFormComponent } from './component/forexecutive/post-form/post-form.component';
import { VendorRequestsComponent } from './component/forexecutive/vendor-requests/vendor-requests.component';
import { NotLoggedInComponent } from './page/not-logged-in/not-logged-in.component';
import { CertificateComponent } from './page/certificate/certificate.component';
import { ViewcertificateComponent } from './component/forexecutive/viewcertificate/viewcertificate.component';

export const routes: Routes = [
    {
        path: '' , component: AuthpageComponent, children: [
            {
                path: '',component: LoginComponent
            },
            {
                path:'login', component : LoginComponent
            },
            {
                path:'sign-up', component : SignupComponent
            },
            {
                path: 'back',  component: NotLoggedInComponent
            },
            {
                path: 'certificate', component: CertificateComponent
            }
        ]
    },  
    {
        path: 'executive', component : ExecutiveComponent, children: [
            {
                path: '', component: ExecutiveLogtableComponent
            },
            {
                path:'logt',component: ExecutiveLogtableComponent
            },
            {
                path:'request',component: ExecutiveRequestsComponent, children: [
                    {path: '', component: HotelRequestsComponent},
                    {path: 'flight', component: FlightRequestsComponent},
                    {path: 'hotel', component: HotelRequestsComponent},
                    {path: 'vendor', component: VendorRequestsComponent},
                    {path: 'viewcertificate', component:ViewcertificateComponent}
                ]
            },
            {
                path:'performance',component: ExecutivePerformanceComponent, children: [
                    {path: '', component: CustomerPerformanceComponent},
                    {path: 'customer', component: CustomerPerformanceComponent},
                    {path: 'vendor', component: VendorPerformanceComponent},
                    {path: 'entity', component: EntityPerformanceComponent},
                    {
                        path:'detailedc',component: DetailedCustomerPerformanceComponent
                    },
                    {
                        path:'detailedv',component: DetailedVendorPerformanceComponent
                    },
                    {
                        path:'detailedhp',component: DetailedHotelPerformanceComponent
                    },
                    {
                        path:'detailedfp',component: DetailedFlightPerformanceComponent
                    }
                ]
            },
            {
                path: 'formex', component: PostFormComponent
            }
        ]
    }
];