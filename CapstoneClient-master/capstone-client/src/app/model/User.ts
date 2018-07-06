export class User {
  username: string;
  image: string;
  name: string;
  email: string;
  id: string;
  providerName: string = null;
  role = {
    id: ''
  };
  constructor(data: any, provider: string) {
    if (data) {
     if(data.image){
       this.image = data.image;
     }else{
       this.image = '../../../assets/image/avatar-default.jpg';
     }
     if(data.name){
       this.name = data.name;
     }else{
       this.name = data.username;
     }
     if(data.userId){
       this.id = data.userId;
     }else{
       this.id = data.id;
     }
     if(data.uid){
       this.id = data.uid;
     }
     if(data.provider || provider){
       this.providerName = provider;
     }
     this.email = data.email;
     if(data.role){
       this.role.id = data.role.id;
     }else{
       this.role.id = "1";
     }
    }
  }
  // public getRole(){
  //   return this.role;
  // }
}
