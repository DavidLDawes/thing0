(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('DataDeleteController',DataDeleteController);

    DataDeleteController.$inject = ['$uibModalInstance', 'entity', 'Data'];

    function DataDeleteController($uibModalInstance, entity, Data) {
        var vm = this;

        vm.data = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Data.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
