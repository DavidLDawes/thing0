(function() {
    'use strict';

    angular
        .module('thingApp')
        .controller('CommandController', CommandController);

    CommandController.$inject = ['$scope', '$state', 'Command'];

    function CommandController ($scope, $state, Command) {
        var vm = this;
        
        vm.commands = [];

        loadAll();

        function loadAll() {
            Command.query(function(result) {
                vm.commands = result;
            });
        }
    }
})();
