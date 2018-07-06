import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ReviewService} from "../../../service/review/review.service";
import {UniversityService} from "../../../service/university/university.service";
import {ToastsManager} from "ng2-toastr";

@Component({
  selector: 'app-approve-question',
  templateUrl: './approve-question.component.html',
  styleUrls: ['./approve-question.component.less']
})
export class ApproveQuestionComponent implements OnInit {
  public listQuestion: any[];
  public question;
  public tags;
  private currentId: number;
  public all = true;
  public show = true;
  constructor(private reviewService: ReviewService,
              private universityService: UniversityService,
              public toastr: ToastsManager, vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr)
  }

  ngOnInit() {
    this.universityService.broadcastTextChange("DANH SÁCH CÂU HỎI ĐỢI DUYỆT");
    this.reviewService.getQuestionNeedApprove().subscribe(res=>{
      if(res){
        this.listQuestion = res;
        this.all = true;
        this.show = true;
      }
    });
  }

  viewDetail(value){
    this.currentId = value;
    this.listQuestion.forEach(x=>{
      if(x.id == value){
        console.log(x);
        this.question = x;
      }
    });
    this.reviewService.getTagQuestion(value).subscribe(res=>{
        this.tags = res;
    });
  }
  approveQuestion(){
    let data={
      'id': this.currentId,
      'status': true,
      'isActive': true
    };
    this.universityService.changeStatusQA(data).subscribe(res=>{
      if(res){
        for (let i = 0; i < this.listQuestion.length; i++) {
          if (this.currentId == this.listQuestion[i].id) {
            this.listQuestion.splice(i, 1);
            this.toastr.success('Câu hỏi này đã được chấp nhận', 'Thành công', {showCloseButton: true});
            if(this.show == true){
              this.reviewService.numberQuestionChange(-1);
            }
            return;
          }
        }
      }
    },err=>{
      this.toastr.error('Không thể kết nối tới máy chủ. Vui lòng kiểm tra lại', 'Thất bại',{showCloseButton: true})
    });
  }
  notApproveQuestion(){
    let data={
      'id': this.currentId,
      'status': false,
      'isActive': false
    };
    this.universityService.changeStatusQA(data).subscribe(res=>{
      if(res){
        for (let i = 0; i < this.listQuestion.length; i++) {
          if (this.currentId == this.listQuestion[i].id) {
            this.listQuestion.splice(i, 1);
            this.toastr.warning('Câu hỏi này đã không được chấp nhận', 'Thông báo', {showCloseButton: true});
            if(this.show == true){
              this.reviewService.numberQuestionChange(-1);
            }
            return;
          }
        }
      }
    },err=>{
      this.toastr.error('Không thể kết nối tới máy chủ. Vui lòng kiểm tra lại', 'Thất bại',{showCloseButton: true})
    });
  }

  showAllQuestion(){
    this.universityService.getAllQuestion().subscribe(res=>{
      this.universityService.broadcastTextChange("DANH SÁCH CÂU HỎI ĐÃ DUYỆT");
      this.listQuestion = res;
      this.all = false;
      this.show = false;
    })
  }
  showQuestionNot(){
    this.universityService.getQuestionNotApprove().subscribe(res=>{
      this.universityService.broadcastTextChange("DANH SÁCH CÂU HỎI KHÔNG DUYỆT");
      this.listQuestion = res;
      this.all = true;
      this.show = false;
    })
  }
}
