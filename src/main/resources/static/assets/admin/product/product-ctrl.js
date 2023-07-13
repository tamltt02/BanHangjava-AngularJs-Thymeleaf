app.controller("product-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.cates = [];
  $scope.form = {};

  $scope.init = function () {
    //load product
    $http.get("/rest/products").then((resp) => {
      $scope.items = resp.data;
      $scope.items.forEach((item) => {
        item.createDate = new Date(item.createDate);
      });
    });
    $http.get("/rest/categories").then((resp) => {
      $scope.cates = resp.data;
    });
  };

  $scope.init();


  //reset
  $scope.reset = function (){
    $scope.form ={
      createDate :new Date(),
      image:'cloud-upload.jpg',
      available:true,
    }
  }

//them
  $scope.create = function (){
    let item = angular.copy($scope.form);
    $http.post('/rest/products',item)
        .then(resp =>{
          resp.data.createDate= new Date(resp.data.createDate);
          $scope.items.push(resp.data);
          $scope.reset();
          alert("Them thanh cong")
        }).catch(error =>{
          alert("Loi roi")
    })
  }
  //update
  $scope.update = function (){
    let item = angular.copy($scope.form);
    $http.put(`/rest/products/${item.id}`,item)
        .then(resp =>{
          let index = $scope.items.findIndex(p => p.id ==  item.id);
          $scope.items[index] = item;
          alert("cap nhap thanh cong");
        })
        .catch(error =>{
          alert("Loi roi");
        })
  }

  //xoa
  $scope.delete = function (item){
      $http.delete(`/rest/products/${item.id}`)
          .then(resp =>{
              const index = $scope.items.findIndex(p => p.id ==  item.id);
              $scope.items.splice(index,1);
              $scope.reset();
              alert("xoa thanh cong");
          })
          .catch(error =>{
              alert("Loi roi");
              console.log("eror"+ error);
          })
  }

  //form edit
  $scope.edit = function (item) {
    $scope.form = angular.copy(item);
    $(".nav-tabs a:eq(0)").tab("show");
  };

  $scope.imageChanged = function (files) {
    let data = new FormData();
    data.append('file', files[0]);
    $http
      .post('/rest/upload/images',data,{
        transformRequest: angular.identity,
        headers: { 'Content-Type': undefined }
      })
      .then(resp => {
        $scope.form.image = resp.data.name;
      })
      .catch(error => {
        alert("Loi upload");
        console.log(error);
      })
  };

  $scope.pager = {
      page :0,
      size:10,
      get items(){
          let start = this.page * this.size;
         return $scope.items.slice(start, start + this.size);
      },
      get count(){
          return Math.ceil(1.0 * $scope.items.length / this.size);
      },
      first(){
        this.page = 0;
      },
      next(){
        this.page++;
          if(this.page> this.count){
              this.first();
          }
      },
      prev(){
        this.page--;
        if(this.page < 0){
            this.last();
        }
      },
      last(){
        this.page = this.count - 1;
      }
  }
});
