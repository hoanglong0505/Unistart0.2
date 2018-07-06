import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {User} from "../../model/User";
import {BaseService} from "../../service/base-service/base.service";
import {RequestOptions, Headers} from "@angular/http";
import {UniversityService} from "../../service/university/university.service";
import {Constants} from "../../constants";
import {LoginService} from "../../service/login/login.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.less']
})
export class UserDetailComponent implements OnInit {
  private user : User;
  public imageSrc;
  public isLoadImage: boolean = false;
  constructor(private baseService: BaseService, private uniService: UniversityService, private constant: Constants,
              private loginService: LoginService, public toastr: ToastsManager) {}

  ngOnInit(){
    this.user = this.baseService.getUser();
    this.imageSrc = this.user.image;
    console.log(this.user.providerName);
  }
  handleInputChange(e){
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /image-*/;
    var reader = new FileReader();
    if (!file.type.match(pattern)) {
      alert('invalid format');
      return;
    }
    this.isLoadImage = true;
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file)
  }

  _handleReaderLoaded(e) {
    var reader = e.target;
    let url = "https://api.imgur.com/3/image";
    var headers = new Headers();
    headers.append('Authorization', 'Client-ID bf915d4106b6639');
    let options = new RequestOptions({ headers: headers });
    let data = {
      'image': reader.result.split(',')[1]
    };
      this.uniService.uploadFile(url,data,options).subscribe((response:any)=>{
        this.imageSrc = response.data.link;
        this.isLoadImage = false;
      });
  }

  onSubmit(form: NgForm){
      let data = {
        'name': form.value.name,
        'email': form.value.email,
        'password': form.value.password,
        'image': this.imageSrc,
        'id': this.user.id
      };
      this.loginService.editProfile(this.constant.EDIT_PROFILE,data).subscribe(res=>{
          this.toastr.success('Bạn đã chỉnh sửa thông tin thành công','Thành công', {showCloseButton: true});
        this.baseService.setUser(res,this.user.providerName);
      },error=>{
        if(error.status = this.constant.UNAUTHORIZED){
          this.toastr.error('Đã xãy ra lỗi. Vui lòng kiểm tra lại','Lỗi', {showCloseButton: true});
        }else{
          this.toastr.error('Không thể kết nối máy chủ. Vui lòng kiểm tra lại.','Thất bại', {showCloseButton: true});
        }
      });
  }
}
