(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('DataDetailController', DataDetailController);

    DataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Data'];

    function DataDetailController($scope, $rootScope, $stateParams, previousState, entity, Data) {
        var vm = this;

        vm.data = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('thingApp:dataUpdate', function(event, result) {
            vm.data = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
