--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2014-06-28 15:59:39 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 201 (class 3079 OID 11791)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3378 (class 0 OID 0)
-- Dependencies: 201
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 202 (class 3079 OID 21600)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 3379 (class 0 OID 0)
-- Dependencies: 202
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 22897)
-- Name: local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE local (
    id integer NOT NULL,
    lat double precision NOT NULL,
    lon double precision NOT NULL,
    cidade character varying NOT NULL,
    estado character varying(2) NOT NULL,
    pais character varying NOT NULL,
    nome character varying,
    login_usuario_insercao character varying NOT NULL,
    bairro character varying DEFAULT 'asdas'::character varying
);


ALTER TABLE public.local OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 22895)
-- Name: Local_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Local_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Local_id_seq" OWNER TO postgres;

--
-- TOC entry 3380 (class 0 OID 0)
-- Dependencies: 183
-- Name: Local_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Local_id_seq" OWNED BY local.id;


--
-- TOC entry 197 (class 1259 OID 23124)
-- Name: avaliacao_evento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliacao_evento (
    nota integer NOT NULL,
    data_hora integer NOT NULL,
    id_evento integer NOT NULL,
    login_usuario character varying NOT NULL,
    id_local integer NOT NULL
);


ALTER TABLE public.avaliacao_evento OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 23176)
-- Name: avaliacao_imagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliacao_imagem (
    nota integer NOT NULL,
    data_hora integer NOT NULL,
    url_imagem character varying NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.avaliacao_imagem OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 23142)
-- Name: avaliacao_local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliacao_local (
    nota integer NOT NULL,
    data_hora integer NOT NULL,
    id_local integer NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.avaliacao_local OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 23194)
-- Name: avaliacao_video; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliacao_video (
    nota integer NOT NULL,
    data_hora integer NOT NULL,
    url_video character varying NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.avaliacao_video OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 22981)
-- Name: esporte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE esporte (
    nome character varying NOT NULL
);


ALTER TABLE public.esporte OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 22989)
-- Name: esporte_tem_modalidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE esporte_tem_modalidade (
    nome_esporte character varying NOT NULL,
    nome_modalidade character varying NOT NULL
);


ALTER TABLE public.esporte_tem_modalidade OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 23041)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evento (
    id integer NOT NULL,
    titulo character varying NOT NULL,
    data_hora timestamp without time zone NOT NULL,
    descricao character varying,
    tipo integer NOT NULL,
    id_local integer NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 3381 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN evento.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN evento.tipo IS '1 - Estou aqui
2 - Estarei aqui
3 - Competição
4 - Apresentação';


--
-- TOC entry 193 (class 1259 OID 23039)
-- Name: evento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE evento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.evento_id_seq OWNER TO postgres;

--
-- TOC entry 3382 (class 0 OID 0)
-- Dependencies: 193
-- Name: evento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE evento_id_seq OWNED BY evento.id;


--
-- TOC entry 196 (class 1259 OID 23080)
-- Name: imagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE imagem (
    data_hora timestamp without time zone NOT NULL,
    url character varying NOT NULL,
    altura integer NOT NULL,
    largura integer NOT NULL,
    id_local integer NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.imagem OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 22965)
-- Name: modalidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE modalidade (
    nome character varying NOT NULL,
    descricao character varying NOT NULL
);


ALTER TABLE public.modalidade OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 22973)
-- Name: modalidade_usa_tipo_obstaculo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE modalidade_usa_tipo_obstaculo (
    nome_modalidade character varying NOT NULL,
    nome_tipo_obstaculo character varying NOT NULL
);


ALTER TABLE public.modalidade_usa_tipo_obstaculo OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 22906)
-- Name: tipo_obstaculo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_obstaculo (
    nome character varying NOT NULL
);


ALTER TABLE public.tipo_obstaculo OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 22914)
-- Name: tipo_obstaculo_em_local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_obstaculo_em_local (
    id_local integer NOT NULL,
    nome_tipo_obstaculo character varying NOT NULL
);


ALTER TABLE public.tipo_obstaculo_em_local OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 23007)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    nome character varying NOT NULL,
    login character varying NOT NULL,
    senha character varying
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 23016)
-- Name: usuario_pratica_esporte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario_pratica_esporte (
    login_usuario character varying NOT NULL,
    nome_esporte character varying NOT NULL
);


ALTER TABLE public.usuario_pratica_esporte OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 23062)
-- Name: video; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE video (
    data_hora timestamp without time zone NOT NULL,
    url character varying NOT NULL,
    duracao integer NOT NULL,
    id_local integer NOT NULL,
    login_usuario character varying NOT NULL
);


ALTER TABLE public.video OWNER TO postgres;

--
-- TOC entry 3185 (class 2604 OID 23044)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evento ALTER COLUMN id SET DEFAULT nextval('evento_id_seq'::regclass);


--
-- TOC entry 3183 (class 2604 OID 22900)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local ALTER COLUMN id SET DEFAULT nextval('"Local_id_seq"'::regclass);


--
-- TOC entry 3211 (class 2606 OID 23131)
-- Name: pk_avaliacao_evento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliacao_evento
    ADD CONSTRAINT pk_avaliacao_evento PRIMARY KEY (id_evento, login_usuario);


--
-- TOC entry 3215 (class 2606 OID 23183)
-- Name: pk_avaliacao_imagem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliacao_imagem
    ADD CONSTRAINT pk_avaliacao_imagem PRIMARY KEY (url_imagem, login_usuario);


--
-- TOC entry 3213 (class 2606 OID 23149)
-- Name: pk_avaliacao_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliacao_local
    ADD CONSTRAINT pk_avaliacao_local PRIMARY KEY (id_local, login_usuario);


--
-- TOC entry 3217 (class 2606 OID 23201)
-- Name: pk_avaliacao_video; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avaliacao_video
    ADD CONSTRAINT pk_avaliacao_video PRIMARY KEY (url_video, login_usuario);


--
-- TOC entry 3199 (class 2606 OID 22996)
-- Name: pk_esporte_tem_modalidade; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY esporte_tem_modalidade
    ADD CONSTRAINT pk_esporte_tem_modalidade PRIMARY KEY (nome_esporte, nome_modalidade);


--
-- TOC entry 3205 (class 2606 OID 23061)
-- Name: pk_evento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evento
    ADD CONSTRAINT pk_evento PRIMARY KEY (id, id_local);


--
-- TOC entry 3209 (class 2606 OID 23087)
-- Name: pk_imagem; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imagem
    ADD CONSTRAINT pk_imagem PRIMARY KEY (url);


--
-- TOC entry 3187 (class 2606 OID 22905)
-- Name: pk_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT pk_local PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 22972)
-- Name: pk_modalidade; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY modalidade
    ADD CONSTRAINT pk_modalidade PRIMARY KEY (nome);


--
-- TOC entry 3195 (class 2606 OID 22980)
-- Name: pk_modalidade_usa_tipo_obstaculo; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY modalidade_usa_tipo_obstaculo
    ADD CONSTRAINT pk_modalidade_usa_tipo_obstaculo PRIMARY KEY (nome_modalidade, nome_tipo_obstaculo);


--
-- TOC entry 3197 (class 2606 OID 22988)
-- Name: pk_nome; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY esporte
    ADD CONSTRAINT pk_nome PRIMARY KEY (nome);


--
-- TOC entry 3201 (class 2606 OID 23014)
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (login);


--
-- TOC entry 3203 (class 2606 OID 23023)
-- Name: pk_usuario_pratica_esporte; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario_pratica_esporte
    ADD CONSTRAINT pk_usuario_pratica_esporte PRIMARY KEY (login_usuario, nome_esporte);


--
-- TOC entry 3207 (class 2606 OID 23069)
-- Name: pk_video; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY video
    ADD CONSTRAINT pk_video PRIMARY KEY (url);


--
-- TOC entry 3191 (class 2606 OID 22925)
-- Name: tipo_obstaculo_em_local_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_obstaculo_em_local
    ADD CONSTRAINT tipo_obstaculo_em_local_pkey PRIMARY KEY (id_local, nome_tipo_obstaculo);


--
-- TOC entry 3189 (class 2606 OID 22913)
-- Name: tipo_obstaculo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_obstaculo
    ADD CONSTRAINT tipo_obstaculo_pkey PRIMARY KEY (nome);


--
-- TOC entry 3231 (class 2606 OID 23132)
-- Name: fk_avaliacao_evento_login_usuario_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_evento
    ADD CONSTRAINT fk_avaliacao_evento_login_usuario_evento FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3232 (class 2606 OID 23137)
-- Name: fk_avaliacao_evento_pk_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_evento
    ADD CONSTRAINT fk_avaliacao_evento_pk_evento FOREIGN KEY (id_evento, id_local) REFERENCES evento(id, id_local);


--
-- TOC entry 3236 (class 2606 OID 23189)
-- Name: fk_avaliacao_imagem_login_usuario_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_imagem
    ADD CONSTRAINT fk_avaliacao_imagem_login_usuario_evento FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3234 (class 2606 OID 23155)
-- Name: fk_avaliacao_local_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_local
    ADD CONSTRAINT fk_avaliacao_local_id_local FOREIGN KEY (id_local) REFERENCES local(id);


--
-- TOC entry 3233 (class 2606 OID 23150)
-- Name: fk_avaliacao_local_login_usuario_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_local
    ADD CONSTRAINT fk_avaliacao_local_login_usuario_evento FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3235 (class 2606 OID 23184)
-- Name: fk_avaliacao_url_imagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_imagem
    ADD CONSTRAINT fk_avaliacao_url_imagem FOREIGN KEY (url_imagem) REFERENCES imagem(url);


--
-- TOC entry 3238 (class 2606 OID 23207)
-- Name: fk_avaliacao_url_video; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_video
    ADD CONSTRAINT fk_avaliacao_url_video FOREIGN KEY (url_video) REFERENCES video(url);


--
-- TOC entry 3237 (class 2606 OID 23202)
-- Name: fk_avaliacao_video_login_usuario_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao_video
    ADD CONSTRAINT fk_avaliacao_video_login_usuario_evento FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3219 (class 2606 OID 22931)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_obstaculo_em_local
    ADD CONSTRAINT fk_id_local FOREIGN KEY (id_local) REFERENCES local(id);


--
-- TOC entry 3225 (class 2606 OID 23050)
-- Name: fk_id_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evento
    ADD CONSTRAINT fk_id_local FOREIGN KEY (id_local) REFERENCES local(id);


--
-- TOC entry 3228 (class 2606 OID 23075)
-- Name: fk_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY video
    ADD CONSTRAINT fk_local FOREIGN KEY (id_local) REFERENCES local(id);


--
-- TOC entry 3229 (class 2606 OID 23088)
-- Name: fk_local_imagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY imagem
    ADD CONSTRAINT fk_local_imagem FOREIGN KEY (id_local) REFERENCES local(id);


--
-- TOC entry 3223 (class 2606 OID 23024)
-- Name: fk_login_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario_pratica_esporte
    ADD CONSTRAINT fk_login_usuario FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3226 (class 2606 OID 23055)
-- Name: fk_login_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evento
    ADD CONSTRAINT fk_login_usuario FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3218 (class 2606 OID 23034)
-- Name: fk_login_usuario_insercao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local
    ADD CONSTRAINT fk_login_usuario_insercao FOREIGN KEY (login_usuario_insercao) REFERENCES usuario(login);


--
-- TOC entry 3221 (class 2606 OID 22997)
-- Name: fk_nome_esporte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY esporte_tem_modalidade
    ADD CONSTRAINT fk_nome_esporte FOREIGN KEY (nome_esporte) REFERENCES esporte(nome);


--
-- TOC entry 3224 (class 2606 OID 23029)
-- Name: fk_nome_esporte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario_pratica_esporte
    ADD CONSTRAINT fk_nome_esporte FOREIGN KEY (nome_esporte) REFERENCES esporte(nome);


--
-- TOC entry 3222 (class 2606 OID 23002)
-- Name: fk_nome_modalidade; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY esporte_tem_modalidade
    ADD CONSTRAINT fk_nome_modalidade FOREIGN KEY (nome_modalidade) REFERENCES modalidade(nome);


--
-- TOC entry 3220 (class 2606 OID 22936)
-- Name: fk_nome_tipo_obstaculo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_obstaculo_em_local
    ADD CONSTRAINT fk_nome_tipo_obstaculo FOREIGN KEY (nome_tipo_obstaculo) REFERENCES tipo_obstaculo(nome);


--
-- TOC entry 3227 (class 2606 OID 23070)
-- Name: fk_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY video
    ADD CONSTRAINT fk_usuario FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3230 (class 2606 OID 23093)
-- Name: fk_usuario_imagem; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY imagem
    ADD CONSTRAINT fk_usuario_imagem FOREIGN KEY (login_usuario) REFERENCES usuario(login);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-06-28 15:59:40 BRT

--
-- PostgreSQL database dump complete
--

