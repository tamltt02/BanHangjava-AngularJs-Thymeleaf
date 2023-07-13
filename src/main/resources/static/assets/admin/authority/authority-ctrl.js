app.controller("authority-ctrl", function ($scope, $http) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.init = function (){
        //load all role
        $http.get("/rest/roles").then(resp =>{
            $scope.roles = resp.data;
        })
        // load staff and director
        $http.get("/rest/accounts?admin=true").then(resp =>{
            $scope.admins = resp.data;
        })
        // load authority of staff and director
        $http.get("/rest/authorities?admin=true").then(resp =>{
            $scope.authorities = resp.data;
        }).catch(error =>{
            $location.path("/unauthorized")
        })
    }

    $scope.authority_of = function (acc,role){
        if ($scope.authorities){
            return $scope.authorities.find( ur => ur.account.username == acc.username && ur.role.id == role.id);
        }
    }

    $scope.authority_changed = function (acc,role){
        let authority = $scope.authority_of(acc,role);
        if (authority){// đã cấp quyền => thu hồi quyền
             $scope.revoke_authority(authority);
        }else{
            authority = {account: acc, role : role};
            $scope.grant_authority(authority);
        }
    }

    $scope.grant_authority = function (authoity) {
        $http.post(`/rest/authorities`,authoity).then(resp =>{
            $scope.authorities.push(resp.data);
            alert("Cap quyền thành công");
        }).catch(error =>{
            alert("Thất bại");
            console.log(error);
        })
    }
    $scope.revoke_authority = function (authoity) {
        $http.delete(`/rest/authorities/${authoity.id}`).then(resp =>{
            let index = $scope.authorities.findIndex(a => a.id == authoity.id);
            $scope.authorities.splice(index,1);
            alert("Thu hồi thành công");
        }).catch(error =>{
            alert("Thất bại");
            console.log(error);
        })
    }
    $scope.init();
})