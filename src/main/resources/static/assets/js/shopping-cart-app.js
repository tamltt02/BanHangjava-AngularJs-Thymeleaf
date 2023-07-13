let app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    $scope.cart = {
        items: [],
        add(id) {
            let item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        //Xoa sp
        remove(id) {
            let index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);// xoa 1 phan tu , tu index
            this.saveToLocalStorage();
        },

        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //Lưu giỏ hàng vào local Sotrage
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage() {
            let json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },
        //Tổng số mặt hàng trong giỏ
        get count() {
            return this.items.map(item => item.qty)//
                // tính toán
                .reduce((total, qty) => total += qty, 0);
        },
        // Tổng tiền trong giỏ
        get amount() {
            return this.items.map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        }
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address: "",
        account: {username: $("#username").text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            })
        },
        purchase() {
            let order = angular.copy(this);
            //dat hang
            $http.post("/rest/orders",order).then(resp=>{
                alert("Dat hang thanh cong");
                $scope.cart.clear();
                location.href ="/order/detail/" + resp.data.id;
            }).catch(error =>{
                alert("Dat hang that bai")
                console.log(error)
            })
        }
    }
})