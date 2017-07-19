--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admins; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE admins (
    id integer NOT NULL,
    username character varying,
    password character varying,
    numofbookscheckedout integer
);


ALTER TABLE admins OWNER TO "Guest";

--
-- Name: admins_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE admins_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admins_id_seq OWNER TO "Guest";

--
-- Name: admins_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE admins_id_seq OWNED BY admins.id;


--
-- Name: books; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE books (
    id integer NOT NULL,
    title character varying,
    author character varying,
    year integer,
    checkedout boolean,
    checkedoutby integer,
    duedate timestamp without time zone,
    description character varying,
    imageurl character varying
);


ALTER TABLE books OWNER TO "Guest";

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE books_id_seq OWNER TO "Guest";

--
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE books_id_seq OWNED BY books.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE users (
    id integer NOT NULL,
    username character varying,
    password character varying,
    numofbookscheckedout integer
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY admins ALTER COLUMN id SET DEFAULT nextval('admins_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY books ALTER COLUMN id SET DEFAULT nextval('books_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY admins (id, username, password, numofbookscheckedout) FROM stdin;
1	library	admin	0
\.


--
-- Name: admins_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('admins_id_seq', 1, true);


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY books (id, title, author, year, checkedout, checkedoutby, duedate, description, imageurl) FROM stdin;
9	The Exorcist	William Peter Blatty	1971	f	0	\N	An elderly Jesuit priest named Father Lankester Merrin is leading an archaeological dig in northern Iraq and is studying ancient relics. After discovering a small statue of the demon Pazuzu (an actual ancient Assyrian demigod), a series of omens alerts him to a pending confrontation with a powerful evil, which, unknown to the reader at this point, he has battled before in an exorcism in Africa.  Meanwhile, in Georgetown, a young girl named Regan MacNeil is living with her famous mother, actress Chris MacNeil, who is in Georgetown filming a movie. As Chris finishes her work on the film, Regan begins to become inexplicably ill. After a gradual series of poltergeist-like disturbances in their rented house, for which Chris attempts to find rational explanations, Regan begins to rapidly undergo disturbing psychological and physical changes: she refuses to eat or sleep, becomes withdrawn and frenetic, and increasingly aggressive and violent.	/images/the-exorcist-blatty.jpg
11	Consider the Lobster	David Foster Wallace	2005	f	0	2017-07-20 00:35:57.465	Consider the Lobster and Other Essays is a collection of essays by novelist David Foster Wallace. It is also the title of one of the essays, which was published in Gourmet magazine in 2004.	/images/consider-the-lobster-wallace.jpg
2	Harry Potter and the Sorcerers Stone	J.K Rowling	1997	f	0	2017-07-20 00:27:20.934	For 10 years, Harry lives in the cupboard under the stairs and is subjected to cruel mistreatment by Aunt Petunia, Uncle Vernon and their son Dudley. On his 11th birthday, Harry receives a letter inviting him to study magic at the Hogwarts School of Witchcraft and Wizardry.	/images/harry-potter-rowling.jpg
1	Mother Night	Kurt Vonnegut	1961	f	0	2017-07-20 00:36:14.057	Mother Night is a daring challenge to our moral sense. American Howard W. Campbell, Jr., a spy during World War II, is now on trial in Israel as a Nazi war criminal. But is he really guilty? In this brilliant book rife with true gallows humor, Vonnegut turns black and white into a chilling shade of gray with a verdict that will haunt us all.	/images/mother-night-vonnegut.jpg
3	The Chosen	Chaim Potok	1967	f	0	2017-07-20 00:38:01.034	The Chosen is set in the mid-twentieth Century, in Williamsburg, Brooklyn, New York City. The story takes place over a period of six years, beginning in 1944 when the protagonists are fifteen years old. It is set against the backdrop of the historical events of the time: the death of President Roosevelt, the end of World War II, the revelation of the Holocaust in Europe, and the struggle for the creation of the state of Israel.[4]	/images/the-chosen-potok.jpg
8	Infinite Jest	David Foster Wallace	1996	f	0	2017-07-18 22:05:38.447	In the novel's future world, the United States, Canada, and Mexico together compose a unified North American superstate known as the Organization of North American Nations, or O.N.A.N. (an allusion to onanism).[9] Corporations are allowed the opportunity to bid for and purchase naming rights for each calendar year, replacing traditional numerical designations with ostensibly honorary monikers bearing corporate names. Although the narrative is fragmented and spans several "named" years, most of the story takes place during "The Year of the Depend Adult Undergarment" (Y.D.A.U.). On the orders of U.S. President Johnny Gentle (a "clean freak" who campaigned on the platform of cleaning up the USA while ensuring that no American would be caused any discomfort in the process), much of what used to be the northeastern United States and southeastern Canada has become a giant hazardous waste dump, an area "given" to Canada and known as the "Great Concavity" by Americans due to the resulting displacement of the border.  The novel's primary locations are the Enfield Tennis Academy ("ETA") and Ennet House Drug and Alcohol Recovery House (separated by a hillside in suburban Boston, Massachusetts), and a mountainside outside of Tucson, Arizona. Many characters are students or faculty at the school or patients or staff at the halfway house; a multi-part, philosophical conversation between a Quebec separatist and his US government contact occurs at the Arizona location.	/images/infinite-jest-wallace.jpg
\.


--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('books_id_seq', 12, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, username, password, numofbookscheckedout) FROM stdin;
3	red	red	0
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 3, true);


--
-- Name: admins_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);


--
-- Name: books_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

