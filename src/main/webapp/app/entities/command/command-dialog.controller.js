(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('CommandDialogController', CommandDialogController);

    CommandDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Command'];

    function CommandDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Command) {
        var vm = this;

        vm.command = entity;
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
            if (vm.command.id !== null) {
                Command.update(vm.command, onSaveSuccess, onSaveError);
            } else {
                Command.save(vm.command, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('thingApp:commandUpdate', result);
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
