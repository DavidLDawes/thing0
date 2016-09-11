(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('DataController', DataController);

    DataController.$inject = ['$scope', '$state', 'Data'];

    function DataController ($scope, $state, Data) {
        var vm = this;
        
        vm.data = [];

        loadAll();

        function loadAll() {
            Data.query(function(result) {
                vm.data = result;
            });
        }
    }
})();
