(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('DataDialogController', DataDialogController);

    DataDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Data'];

    function DataDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Data) {
        var vm = this;

        vm.data = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.data.id !== null) {
                Data.update(vm.data, onSaveSuccess, onSaveError);
            } else {
                Data.save(vm.data, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('thingApp:dataUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
