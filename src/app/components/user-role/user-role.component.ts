import { FormControl,FormBuilder,FormGroup,Validators,ReactiveFormsModule } from '@angular/forms';
import { UpdateDetails } from './../../models/update-details';
import { UserRole } from './../../models/user-role';
import { UserDetails } from './../../models/user-details';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-role',
  templateUrl: './user-role.component.html',
  styleUrls: ['./user-role.component.scss']
})
export class UserRoleComponent implements OnInit {

  userId:any;
  productForm:any;
  userRecord:Array<any>;
  userRole:Array<any>;
  userUpdate=[];
  userObj:UserDetails =new UserDetails();
  roleObj:UserRole =new UserRole();
  userUpdateObj:UpdateDetails=new UpdateDetails();
  id=Number(localStorage.getItem('userId'));
  
  constructor(private formBuilder: FormBuilder,private router:Router, private userService:UserService,) {
    this. productForm = this.formBuilder.group({
    designation: ['', Validators.required],
    manager: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getAllUserDetails();
    this.getAllUserRole();
  }

  getAllUserDetails(){
    this.userService.getAllUserDetails().subscribe(response =>{
    this.userRecord=response.data;
    console.log("response.data ",this.userRecord);
    })
  }

  getAllUserRole(){
    this.userService.getAllUserRole().subscribe(response =>{
    this.userRole=response.data;
    console.log("response.data ",this.userRole);
    })
  }

  onUpdate(userId: any) {
    //  localStorage.setItem('userId',userId.toString());
    //  location.reload();
      this.userService.getAllUserDetails().subscribe(response =>{
      const n=response.data.length;
      for(var i=0;i<=n-1;i++){
        if(response.data[i]?.userId != userId){
          this.userUpdate.push(response.data[i]);
        }
      } 
      console.log("response.data ",this.userUpdate);
    })
    this.userUpdate.length=0;
  }

  setDataToFormBuilder(object): void { 
    this.userUpdateObj.designation=object.designation,
    this.userUpdateObj.manager=object.manager
  }

  onSubmit(userId: any) {
      this.setDataToFormBuilder(this.userUpdateObj);
      this.userService.updateRecord(this.userUpdateObj,userId).subscribe(response => {
       alert("Id "+userId+" has been Updated.");
      }, _err => {
    })
  //  localStorage.clear();
  }
}
