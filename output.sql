--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5 (Ubuntu 11.5-3.pgdg16.04+1)
-- Dumped by pg_dump version 12.0 (Ubuntu 12.0-2.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: aluno; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.aluno (
    id integer NOT NULL,
    chave_de_acesso character varying(255),
    nome character varying(255),
    turma_id integer
);


ALTER TABLE public.aluno OWNER TO ygwwnggxictsve;

--
-- Name: aluno_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.aluno_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.aluno_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: aluno_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.aluno_id_seq OWNED BY public.aluno.id;


--
-- Name: aviso; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.aviso (
    id integer NOT NULL,
    data_de_criacao timestamp without time zone,
    descricao text,
    imagem text,
    titulo character varying(255)
);


ALTER TABLE public.aviso OWNER TO ygwwnggxictsve;

--
-- Name: aviso_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.aviso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.aviso_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: aviso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.aviso_id_seq OWNED BY public.aviso.id;


--
-- Name: bullying; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.bullying (
    id integer NOT NULL,
    descricao text,
    imagem text,
    link_do_formulario character varying(255)
);


ALTER TABLE public.bullying OWNER TO ygwwnggxictsve;

--
-- Name: bullying_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.bullying_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bullying_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: bullying_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.bullying_id_seq OWNED BY public.bullying.id;


--
-- Name: calendario; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.calendario (
    id integer NOT NULL,
    link_do_calendario character varying(255)
);


ALTER TABLE public.calendario OWNER TO ygwwnggxictsve;

--
-- Name: calendario_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.calendario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.calendario_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: calendario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.calendario_id_seq OWNED BY public.calendario.id;


--
-- Name: disciplinas_professor; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.disciplinas_professor (
    professor_id integer NOT NULL,
    disciplinas character varying(255)
);


ALTER TABLE public.disciplinas_professor OWNER TO ygwwnggxictsve;

--
-- Name: escola; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.escola (
    id integer NOT NULL,
    descricao text,
    imagem text,
    nome character varying(255)
);


ALTER TABLE public.escola OWNER TO ygwwnggxictsve;

--
-- Name: escola_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.escola_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.escola_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: escola_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.escola_id_seq OWNED BY public.escola.id;


--
-- Name: gremio; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.gremio (
    id integer NOT NULL,
    descricao text,
    imagem text,
    link_do_gremio character varying(255)
);


ALTER TABLE public.gremio OWNER TO ygwwnggxictsve;

--
-- Name: gremio_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.gremio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.gremio_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: gremio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.gremio_id_seq OWNED BY public.gremio.id;


--
-- Name: item_topico; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.item_topico (
    id integer NOT NULL,
    link_do_item character varying(255),
    nome character varying(255),
    topico_id integer
);


ALTER TABLE public.item_topico OWNER TO ygwwnggxictsve;

--
-- Name: item_topico_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.item_topico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_topico_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: item_topico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.item_topico_id_seq OWNED BY public.item_topico.id;


--
-- Name: links_aviso; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.links_aviso (
    aviso_id integer NOT NULL,
    link character varying(255),
    nome character varying(255)
);


ALTER TABLE public.links_aviso OWNER TO ygwwnggxictsve;

--
-- Name: links_material; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.links_material (
    material_id integer NOT NULL,
    link character varying(255),
    nome character varying(255)
);


ALTER TABLE public.links_material OWNER TO ygwwnggxictsve;

--
-- Name: material; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.material (
    id integer NOT NULL,
    data_de_criacao timestamp without time zone,
    descricao text,
    imagem text,
    titulo character varying(100),
    turma_id integer
);


ALTER TABLE public.material OWNER TO ygwwnggxictsve;

--
-- Name: material_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.material_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.material_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: material_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.material_id_seq OWNED BY public.material.id;


--
-- Name: noticia; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.noticia (
    id integer NOT NULL,
    data_de_criacao timestamp without time zone,
    descricao text,
    links character varying(255),
    notificavel boolean,
    titulo character varying(255)
);


ALTER TABLE public.noticia OWNER TO ygwwnggxictsve;

--
-- Name: noticia_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.noticia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.noticia_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: noticia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.noticia_id_seq OWNED BY public.noticia.id;


--
-- Name: perfis; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.perfis (
    usuario_id integer NOT NULL,
    perfis integer
);


ALTER TABLE public.perfis OWNER TO ygwwnggxictsve;

--
-- Name: professor; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.professor (
    id integer NOT NULL,
    descricao text,
    nome character varying(255)
);


ALTER TABLE public.professor OWNER TO ygwwnggxictsve;

--
-- Name: professor_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.professor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.professor_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: professor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.professor_id_seq OWNED BY public.professor.id;


--
-- Name: topico; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.topico (
    id integer NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.topico OWNER TO ygwwnggxictsve;

--
-- Name: topico_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.topico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topico_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: topico_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.topico_id_seq OWNED BY public.topico.id;


--
-- Name: turma; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.turma (
    id integer NOT NULL,
    chave_de_acesso character varying(255),
    nome character varying(255)
);


ALTER TABLE public.turma OWNER TO ygwwnggxictsve;

--
-- Name: turma_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.turma_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.turma_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: turma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.turma_id_seq OWNED BY public.turma.id;


--
-- Name: turma_ultimas_chaves; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.turma_ultimas_chaves (
    turma_id integer NOT NULL,
    ultimas_chaves character varying(255)
);


ALTER TABLE public.turma_ultimas_chaves OWNER TO ygwwnggxictsve;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: ygwwnggxictsve
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    senha character varying(255),
    username character varying(255)
);


ALTER TABLE public.usuario OWNER TO ygwwnggxictsve;

--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: ygwwnggxictsve
--

CREATE SEQUENCE public.usuario_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO ygwwnggxictsve;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ygwwnggxictsve
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: aluno id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.aluno ALTER COLUMN id SET DEFAULT nextval('public.aluno_id_seq'::regclass);


--
-- Name: aviso id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.aviso ALTER COLUMN id SET DEFAULT nextval('public.aviso_id_seq'::regclass);


--
-- Name: bullying id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.bullying ALTER COLUMN id SET DEFAULT nextval('public.bullying_id_seq'::regclass);


--
-- Name: calendario id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.calendario ALTER COLUMN id SET DEFAULT nextval('public.calendario_id_seq'::regclass);


--
-- Name: escola id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.escola ALTER COLUMN id SET DEFAULT nextval('public.escola_id_seq'::regclass);


--
-- Name: gremio id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.gremio ALTER COLUMN id SET DEFAULT nextval('public.gremio_id_seq'::regclass);


--
-- Name: item_topico id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.item_topico ALTER COLUMN id SET DEFAULT nextval('public.item_topico_id_seq'::regclass);


--
-- Name: material id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.material ALTER COLUMN id SET DEFAULT nextval('public.material_id_seq'::regclass);


--
-- Name: noticia id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.noticia ALTER COLUMN id SET DEFAULT nextval('public.noticia_id_seq'::regclass);


--
-- Name: professor id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.professor ALTER COLUMN id SET DEFAULT nextval('public.professor_id_seq'::regclass);


--
-- Name: topico id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.topico ALTER COLUMN id SET DEFAULT nextval('public.topico_id_seq'::regclass);


--
-- Name: turma id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.turma ALTER COLUMN id SET DEFAULT nextval('public.turma_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Name: aluno aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT aluno_pkey PRIMARY KEY (id);


--
-- Name: aviso aviso_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.aviso
    ADD CONSTRAINT aviso_pkey PRIMARY KEY (id);


--
-- Name: bullying bullying_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.bullying
    ADD CONSTRAINT bullying_pkey PRIMARY KEY (id);


--
-- Name: calendario calendario_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.calendario
    ADD CONSTRAINT calendario_pkey PRIMARY KEY (id);


--
-- Name: escola escola_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.escola
    ADD CONSTRAINT escola_pkey PRIMARY KEY (id);


--
-- Name: gremio gremio_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.gremio
    ADD CONSTRAINT gremio_pkey PRIMARY KEY (id);


--
-- Name: item_topico item_topico_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.item_topico
    ADD CONSTRAINT item_topico_pkey PRIMARY KEY (id);


--
-- Name: material material_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.material
    ADD CONSTRAINT material_pkey PRIMARY KEY (id);


--
-- Name: noticia noticia_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.noticia
    ADD CONSTRAINT noticia_pkey PRIMARY KEY (id);


--
-- Name: professor professor_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (id);


--
-- Name: topico topico_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.topico
    ADD CONSTRAINT topico_pkey PRIMARY KEY (id);


--
-- Name: turma turma_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.turma
    ADD CONSTRAINT turma_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: disciplinas_professor disciplinas_professor_fk; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.disciplinas_professor
    ADD CONSTRAINT disciplinas_professor_fk FOREIGN KEY (professor_id) REFERENCES public.professor(id);


--
-- Name: turma_ultimas_chaves fk2hif740nfx8khj5tgx4p7abdn; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.turma_ultimas_chaves
    ADD CONSTRAINT fk2hif740nfx8khj5tgx4p7abdn FOREIGN KEY (turma_id) REFERENCES public.turma(id);


--
-- Name: material fk8sbue7egvds7rjudkhxarjxt; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.material
    ADD CONSTRAINT fk8sbue7egvds7rjudkhxarjxt FOREIGN KEY (turma_id) REFERENCES public.turma(id);


--
-- Name: aluno fkehtgr8rih20h4gomh4dd4sbxu; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT fkehtgr8rih20h4gomh4dd4sbxu FOREIGN KEY (turma_id) REFERENCES public.turma(id);


--
-- Name: item_topico fkeuxv0c5efaci0qkbfwmcaxe5o; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.item_topico
    ADD CONSTRAINT fkeuxv0c5efaci0qkbfwmcaxe5o FOREIGN KEY (topico_id) REFERENCES public.topico(id);


--
-- Name: perfis fkiso72ajmkk36lw7dqjva1h8hl; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.perfis
    ADD CONSTRAINT fkiso72ajmkk36lw7dqjva1h8hl FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: links_aviso links_aviso_fk; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.links_aviso
    ADD CONSTRAINT links_aviso_fk FOREIGN KEY (aviso_id) REFERENCES public.aviso(id);


--
-- Name: links_material links_material_fk; Type: FK CONSTRAINT; Schema: public; Owner: ygwwnggxictsve
--

ALTER TABLE ONLY public.links_material
    ADD CONSTRAINT links_material_fk FOREIGN KEY (material_id) REFERENCES public.material(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: ygwwnggxictsve
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO ygwwnggxictsve;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO ygwwnggxictsve;


--
-- PostgreSQL database dump complete
--

