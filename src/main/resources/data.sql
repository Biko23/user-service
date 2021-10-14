INSERT INTO module (module_global_id,name,is_active) VALUES ('e844e561-b746-4735-b482-32f0c59507c0','Tenant',1);
INSERT INTO module (module_global_id,name,is_active) VALUES ('f96411b0-9684-402e-8f25-fd85f6f29ff1','Tenant Member',1);
INSERT INTO module (module_global_id,name,is_active) VALUES ('e9645cca-cc8e-4db2-b678-3febf1cdc915','Tenant Staff',1);
INSERT INTO module (module_global_id,name,is_active) VALUES ('409af2d8-a05a-4a77-8745-5943169626c4','Loan',1);
INSERT INTO module (module_global_id,name,is_active) VALUES ('ff82ab91-ca87-4307-85b5-466ad563b8fc','Savings',1);

INSERT INTO functional_group (functional_group_global_id,name,description,is_default,is_active) VALUES ('a703ff6e-f31a-403b-a543-4186b7564cf6','Member','This is the member group upon online access request',1,1);
INSERT INTO functional_group (functional_group_global_id,name,description,is_default,is_active) VALUES ('d5b56d76-cae5-4f91-95c6-e974a2e55049','Internal Admin','This is the default group on user sign up',1,1);

INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('7db98525-a578-4c69-925d-6a866e832bf1','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','0','0','0','0','0','0',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('3b3c94c6-69f4-4265-936c-f093f504ef21','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','0','0','0','0','0','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('bfe5a58a-552b-4500-a638-c9d8ab4ef070','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','0','0','0','0','0','0',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('db10cfb7-b115-4248-9e70-5b84f724ce5a','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Loan','409af2d8-a05a-4a77-8745-5943169626c4','1','0','0','0','0','0','0','0','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('900d45d8-db34-4af2-aefa-d1b80397ca87','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Savings','ff82ab91-ca87-4307-85b5-466ad563b8fc','1','0','0','0','1','0','0','0','0',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('dafce68f-61a1-463b-8586-f7fb440030ca','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant','e844e561-b746-4735-b482-32f0c59507c0','1','1','1','1','1','1','1','1','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('a30157fb-450b-453c-a082-2de4e6c2d497','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','1','1','1','1','1','1','1','1','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('01ce5ee5-a181-4a23-a40c-58951a377b16','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','1','1','1','1','1','1','1','1','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('8b973fb3-6c36-401d-bb7c-9f9dcdbe5164','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Loan','409af2d8-a05a-4a77-8745-5943169626c4','1','1','1','1','1','1','1','1','1',1);
INSERT INTO functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('650cead5-912c-469c-b5ea-2524e6b02198','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Savings','ff82ab91-ca87-4307-85b5-466ad563b8fc','1','1','1','1','1','1','1','1','1',1);