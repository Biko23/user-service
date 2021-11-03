INSERT INTO user_module (module_global_id,name,description,is_active) VALUES ('e844e561-b746-4735-b482-32f0c59507c0','Tenant','This module is about tenant management',1);
INSERT INTO user_module (module_global_id,name,description,is_active) VALUES ('f96411b0-9684-402e-8f25-fd85f6f29ff1','Tenant Member','This module is about tenant members management',1);
INSERT INTO user_module (module_global_id,name,description,is_active) VALUES ('e9645cca-cc8e-4db2-b678-3febf1cdc915','Tenant Staff','This module is about tenant staff management',1);
INSERT INTO user_module (module_global_id,name,description,is_active) VALUES ('409af2d8-a05a-4a77-8745-5943169626c4','Loan','This module is about loan management',1);
INSERT INTO user_module (module_global_id,name,description,is_active) VALUES ('ff82ab91-ca87-4307-85b5-466ad563b8fc','Transactions','This module is about savings management',1);

INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('a703ff6e-f31a-403b-a543-4186b7564cf6','Member','This is the member group upon online access request',0,NULL,NULL,1,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('d5b56d76-cae5-4f91-95c6-e974a2e55049','Internal Admin','This is the default group on auth sign up',0,NULL,NULL,1,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('4f88e35f-8259-4de0-adeb-b17155dda086','Annual General Assembly','This is the sacco general assembly group',0,NULL,NULL,0,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('1b0609fd-4bdd-422b-bae5-4f263287069d','Audit and Supervision Committee','This is the sacco internal audit committee group',0,NULL,NULL,0,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('fefcb321-b7ef-424b-8692-d470b7ccf59b','Finance Committee','This is the sacco finance committee group',0,NULL,NULL,0,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('67a34fce-dcd1-4dd1-9317-35cab18147bb','Manager','This is the sacco managers group',1,'fefcb321-b7ef-424b-8692-d470b7ccf59b','Finance Committee',0,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('7fe809ca-f26f-46c2-96a5-543c1548bf28','Accountant','This is the sacco accountants group',1,'67a34fce-dcd1-4dd1-9317-35cab18147bb','Manager',0,1);
INSERT INTO user_functional_group (functional_group_global_id,name,description,has_supervisor,supervisor_group_global_id,supervisor_group_name,is_default,is_active) VALUES ('7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Cashier','This is the sacco cashiers group',1,'7fe809ca-f26f-46c2-96a5-543c1548bf28','Accountant',0,1);


INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('7db98525-a578-4c69-925d-6a866e832bf1','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','0','0','0','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('3b3c94c6-69f4-4265-936c-f093f504ef21','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','0','0','0','0','0','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('bfe5a58a-552b-4500-a638-c9d8ab4ef070','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','0','0','0','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('db10cfb7-b115-4248-9e70-5b84f724ce5a','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Loan','409af2d8-a05a-4a77-8745-5943169626c4','1','0','0','0','0','0','0','0','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('900d45d8-db34-4af2-aefa-d1b80397ca87','Member Mapping','Member mapping description','Member','a703ff6e-f31a-403b-a543-4186b7564cf6','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','1','0','0','0','1','0','0','0','0',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('dafce68f-61a1-463b-8586-f7fb440030ca','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant','e844e561-b746-4735-b482-32f0c59507c0','1','1','1','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('a30157fb-450b-453c-a082-2de4e6c2d497','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','1','1','1','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('01ce5ee5-a181-4a23-a40c-58951a377b16','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','1','1','1','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('8b973fb3-6c36-401d-bb7c-9f9dcdbe5164','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Loan','409af2d8-a05a-4a77-8745-5943169626c4','1','1','1','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('650cead5-912c-469c-b5ea-2524e6b02198','Internal Admin Mapping','Internal Admin mapping description','Internal Admin','d5b56d76-cae5-4f91-95c6-e974a2e55049','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','1','1','1','1','1','1','1','1','1',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('c26435ff-ec66-4aed-9463-fd4bcdd84c6d','Annual General Assembly Mapping','Annual General Assembly mapping description','Annual General Assembly','4f88e35f-8259-4de0-adeb-b17155dda086','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','0','0','0','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('c893d8ad-0b8b-4446-84c5-fb68513c1db2','Annual General Assembly Mapping','Annual General Assembly mapping description','Annual General Assembly','4f88e35f-8259-4de0-adeb-b17155dda086','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('87c7361e-e7dc-4dc7-bcad-4becd35cd153','Annual General Assembly Mapping','Annual General Assembly mapping description','Annual General Assembly','4f88e35f-8259-4de0-adeb-b17155dda086','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('ab2817b4-e1b1-4ca3-b561-7301348e0822','Annual General Assembly Mapping','Annual General Assembly mapping description','Annual General Assembly','4f88e35f-8259-4de0-adeb-b17155dda086','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('ff6987ea-914c-4654-ad01-e89e5be70976','Annual General Assembly Mapping','Annual General Assembly mapping description','Annual General Assembly','4f88e35f-8259-4de0-adeb-b17155dda086','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','0','0','0','1','1','1','0','0','0',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('ddbd1bf0-3792-4a29-8239-d7821c6d8544','Audit and Supervision Committee Mapping','Audit and Supervision Committee mapping description','Audit and Supervision Committee','1b0609fd-4bdd-422b-bae5-4f263287069d','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('7e01247b-0bf9-4088-9feb-b9b22445113f','Audit and Supervision Committee Mapping','Audit and Supervision Committee mapping description','Audit and Supervision Committee','1b0609fd-4bdd-422b-bae5-4f263287069d','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('0be99e06-96ca-4032-9e0d-8f313b4a8038','Audit and Supervision Committee Mapping','Audit and Supervision Committee mapping description','Audit and Supervision Committee','1b0609fd-4bdd-422b-bae5-4f263287069d','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('452a80d3-8570-4f72-80b0-cb16d03c5f8e','Audit and Supervision Committee Mapping','Audit and Supervision Committee mapping description','Audit and Supervision Committee','1b0609fd-4bdd-422b-bae5-4f263287069d','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('c768f69a-7571-47fc-9806-e9d1792453f1','Audit and Supervision Committee Mapping','Audit and Supervision Committee mapping description','Audit and Supervision Committee','1b0609fd-4bdd-422b-bae5-4f263287069d','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','0','0','0','1','1','1','0','0','0',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('8afd7462-3f94-432c-8d30-099d7148b2d1','Finance Committee Mapping','Finance Committee mapping description','Finance Committee','fefcb321-b7ef-424b-8692-d470b7ccf59b','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('e2ebd839-9877-408c-b9c6-8cf9074cd3e1','Finance Committee Mapping','Finance Committee mapping description','Finance Committee','fefcb321-b7ef-424b-8692-d470b7ccf59b','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('d8e711ad-27c0-41a4-b004-c41a8b53e901','Finance Committee Mapping','Finance Committee mapping description','Finance Committee','fefcb321-b7ef-424b-8692-d470b7ccf59b','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('4004bb2d-7c69-4321-89c2-f291b7183e86','Finance Committee Mapping','Finance Committee mapping description','Finance Committee','fefcb321-b7ef-424b-8692-d470b7ccf59b','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('de280647-1268-4ab3-a042-0e6c3468478a','Finance Committee Mapping','Finance Committee mapping description','Finance Committee','fefcb321-b7ef-424b-8692-d470b7ccf59b','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','0','0','0','1','1','1','0','0','0',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('32e634e8-df46-4c70-88a7-b958654ab120','Manager Mapping','Manager mapping description','Manager','67a34fce-dcd1-4dd1-9317-35cab18147bb','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('60a9b65b-b291-48f8-a513-43c065a2cac4','Manager Mapping','Manager mapping description','Manager','67a34fce-dcd1-4dd1-9317-35cab18147bb','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','1','1','0','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('53c8d326-2a1e-4fb5-87d6-018f1f6e77bf','Manager Mapping','Manager mapping description','Manager','67a34fce-dcd1-4dd1-9317-35cab18147bb','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','1','1','0','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('5b6d2ac8-ee96-472b-b010-3814c9b10eea','Manager Mapping','Manager mapping description','Manager','67a34fce-dcd1-4dd1-9317-35cab18147bb','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','1','0','1','1','1','1','1','1',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('34df2062-dcef-4690-8440-8917a7d8dc52','Manager Mapping','Manager mapping description','Manager','67a34fce-dcd1-4dd1-9317-35cab18147bb','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','0','1','0','1','1','1','1','1','1',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('95dd9cc8-5855-4baf-be7e-139b255bd65d','Accountant Mapping','Accountant mapping description','Accountant','7fe809ca-f26f-46c2-96a5-543c1548bf28','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','0','0','0','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('ae11599c-bd99-42d6-a72f-f42cfdec1054','Accountant Mapping','Accountant mapping description','Accountant','7fe809ca-f26f-46c2-96a5-543c1548bf28','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('c3d992d7-607f-4a4e-84d4-3fd557f7b502','Accountant Mapping','Accountant mapping description','Accountant','7fe809ca-f26f-46c2-96a5-543c1548bf28','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('4056ad06-6b2d-496a-9370-c55b7779b516','Accountant Mapping','Accountant mapping description','Accountant','7fe809ca-f26f-46c2-96a5-543c1548bf28','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('09da7dec-7d37-4a35-a42c-a8f2f1421ee4','Accountant Mapping','Accountant mapping description','Accountant','7fe809ca-f26f-46c2-96a5-543c1548bf28','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','0','0','0','1','1','1','0','0','0',1);

INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('d5a4391d-13c5-44b4-9e8f-1a6e49f8a6be','Cashier Mapping','Cashier mapping description','Cashier','7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Tenant','e844e561-b746-4735-b482-32f0c59507c0','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('b10c9b7b-df94-4683-abb0-3f8f20c9aa77','Cashier Mapping','Cashier mapping description','Cashier','7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Tenant Member','f96411b0-9684-402e-8f25-fd85f6f29ff1','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('dd46cad7-d36b-41da-bbae-0a35566462c8','Cashier Mapping','Cashier mapping description','Cashier','7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Tenant Staff','e9645cca-cc8e-4db2-b678-3febf1cdc915','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('fac0fd03-2e98-46cc-b73c-6aa5918664c7','Cashier Mapping','Cashier mapping description','Cashier','7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Loan','409af2d8-a05a-4a77-8745-5943169626c4','0','0','0','1','1','1','0','0','0',1);
INSERT INTO user_functional_group_module_mapping (functional_group_module_mapping_global_id,name,description,functional_group_name,functional_group_global_id,module_name,module_global_id,can_create,can_email,can_hard_delete,can_print,can_retrieve,can_search,can_sms,can_soft_delete,can_update,is_active)
VALUES ('0f8c9525-df5b-44a4-996d-5088d62d3eb9','Cashier Mapping','Cashier mapping description','Cashier','7a551998-a1bc-46b1-b8a8-9c1d504b83f9','Transactions','ff82ab91-ca87-4307-85b5-466ad563b8fc','1','1','0','1','1','1','1','1','1',1);