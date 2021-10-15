DROP TABLE IF EXISTS module;
CREATE TABLE public.module
(
    module_global_id uuid NOT NULL,
    access_location character varying(255) COLLATE pg_catalog."default",
    created_by uuid,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    description character varying(255) COLLATE pg_catalog."default",
    hard_delete integer DEFAULT 0,
    is_active integer DEFAULT 1,
    modified_by uuid,
    name character varying(255) COLLATE pg_catalog."default",
    soft_delete integer DEFAULT 0,
    updated_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    working_time character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT module_pkey PRIMARY KEY (module_global_id)
);

DROP TABLE IF EXISTS functional_group;
CREATE TABLE public.functional_group
(
    functional_group_global_id uuid NOT NULL,
    branch_name character varying(255) COLLATE pg_catalog."default",
    created_by uuid,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    description character varying(255) COLLATE pg_catalog."default",
    hard_delete integer DEFAULT 0,
    is_default integer DEFAULT 0,
    is_active integer DEFAULT 1,
    modified_by uuid,
    name character varying(255) COLLATE pg_catalog."default",
    soft_delete integer DEFAULT 0,
    tenant_global_id uuid,
    updated_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    CONSTRAINT functional_group_pkey PRIMARY KEY (functional_group_global_id)
);

DROP TABLE IF EXISTS functional_group_module_mapping;
CREATE TABLE public.functional_group_module_mapping
(
    functional_group_module_mapping_global_id uuid NOT NULL,
    can_create integer DEFAULT 0,
    can_email integer DEFAULT 0,
    can_hard_delete integer DEFAULT 0,
    can_print integer DEFAULT 0,
    can_retrieve integer DEFAULT 0,
    can_search integer DEFAULT 0,
    can_sms integer DEFAULT 0,
    can_soft_delete integer DEFAULT 0,
    can_update integer DEFAULT 0,
    created_by uuid,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    description character varying(255) COLLATE pg_catalog."default",
    functional_group_global_id uuid,
    functional_group_name character varying(255) COLLATE pg_catalog."default",
    hard_delete integer DEFAULT 0,
    is_active integer DEFAULT 1,
    modified_by uuid,
    module_global_id uuid,
    module_name character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    soft_delete integer DEFAULT 0,
    tenant_global_id uuid,
    tenant_name character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP(0),
    CONSTRAINT functional_group_module_mapping_pkey PRIMARY KEY (functional_group_module_mapping_global_id)
);