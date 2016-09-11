(function() {
    'use strict';

    angular
        .module('thingApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('data', {
            parent: 'entity',
            url: '/data',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'thingApp.data.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data/data.html',
                    controller: 'DataController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('data');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('data-detail', {
            parent: 'entity',
            url: '/data/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'thingApp.data.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/data/data-detail.html',
                    controller: 'DataDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('data');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Data', function($stateParams, Data) {
                    return Data.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'data',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('data-detail.edit', {
            parent: 'data-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data/data-dialog.html',
                    controller: 'DataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Data', function(Data) {
                            return Data.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data.new', {
            parent: 'data',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data/data-dialog.html',
                    controller: 'DataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                json: null,
                                xml: null,
                                text: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('data', null, { reload: 'data' });
                }, function() {
                    $state.go('data');
                });
            }]
        })
        .state('data.edit', {
            parent: 'data',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data/data-dialog.html',
                    controller: 'DataDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Data', function(Data) {
                            return Data.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data', null, { reload: 'data' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('data.delete', {
            parent: 'data',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/data/data-delete-dialog.html',
                    controller: 'DataDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Data', function(Data) {
                            return Data.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('data', null, { reload: 'data' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
