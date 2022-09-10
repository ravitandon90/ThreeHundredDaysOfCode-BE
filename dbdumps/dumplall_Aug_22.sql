--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:sFSD5KrIQXXSHAITSimimw==$gmOuVt7rpZrClX6fUOxoFlVx17BRfRK4+q03qvzgjeQ=:eepyU7a0b3sJ1W0cJaJtSaLCUmmSQpY6HMalxUJ7LSM=';






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

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

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

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

SET default_table_access_method = heap;

--
-- Name: problem_description; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.problem_description (
    problem_id character varying(500) NOT NULL,
    title character varying(500) NOT NULL,
    url character varying(500) NOT NULL,
    description character varying,
    complexity character varying(100),
    example character varying(500),
    problem_constraints character varying(500),
    idx integer NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.problem_description OWNER TO postgres;

--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_profile (
    user_id character varying(500) NOT NULL,
    name character varying(500),
    email_id character varying(500) NOT NULL,
    org character varying(500),
    referrer_id character varying(500) NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.user_profile OWNER TO postgres;

--
-- Name: user_submission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_submission (
    submission_id character varying(500) NOT NULL,
    user_id character varying(500) NOT NULL,
    problem_name character varying(500) NOT NULL,
    problem_link character varying(500) NOT NULL,
    solution_link character varying(500) NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.user_submission OWNER TO postgres;

--
-- Data for Name: problem_description; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.problem_description (problem_id, title, url, description, complexity, example, problem_constraints, idx, created_at, updated_at) FROM stdin;
325437c6-340b-429d-a335-029d2a2ef678	Intersection of Two Linked Lists	https://leetcode.com/problems/intersection-of-two-linked-lists/	\N	\N	\N	\N	1	2022-08-17 00:43:20.945343	2022-08-17 00:43:20.945343
0aa8a567-996e-4acc-a05f-d809e9dded7c	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	\N	\N	\N	\N	2	2022-08-17 00:43:20.962157	2022-08-17 00:43:20.962157
07ec08ad-f7ac-424f-ad9d-92680fea1dc9	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	\N	\N	\N	\N	3	2022-08-17 00:43:20.970064	2022-08-17 00:43:20.970064
68d0b06b-4dea-46ca-8139-19bc2c097dca	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	\N	\N	\N	\N	4	2022-08-17 00:43:20.978794	2022-08-17 00:43:20.978794
84bc15c5-8547-404a-9fe5-43f032dddf4c	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	\N	\N	\N	\N	5	2022-08-17 00:43:20.988008	2022-08-17 00:43:20.988008
6d038d18-179a-4177-8b5f-cbcd1a157a0c	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	\N	\N	\N	\N	6	2022-08-17 00:43:20.99596	2022-08-17 00:43:20.99596
d542a65b-ba20-45fd-8bf6-fec246c57673	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	\N	\N	\N	\N	7	2022-08-17 00:43:21.004985	2022-08-17 00:43:21.004985
3b17d834-feb8-4ce1-ba04-1fc5d33af69e	Merge Two Sorted Lists	https://leetcode.com/problems/merge-two-sorted-lists/	\N	\N	\N	\N	8	2022-08-17 00:43:21.012834	2022-08-17 00:43:21.012834
615fb556-84ed-4af2-8fe0-737f4c70939f	Nested List Weight Sum	https://leetcode.com/problems/nested-list-weight-sum/	\N	\N	\N	\N	9	2022-08-17 00:43:21.02184	2022-08-17 00:43:21.02184
4d500239-2752-4d59-afd8-2790973ef648	Given a valid IPv4 address, find a defanged version of that IP address	https://leetcode.com/problems/defanging-an-ip-address/	\N	\N	\N	\N	10	2022-08-17 00:43:21.029805	2022-08-17 00:43:21.029805
5e58a675-1216-461d-ba1e-84242f2d6bcc	Backspace String Compare	https://leetcode.com/problems/backspace-string-compare/	\N	\N	\N	\N	11	2022-08-17 00:43:21.03833	2022-08-17 00:43:21.03833
3c826c4c-d23b-493f-b65d-c324663f3533	Number of matching subsequences in an array	https://leetcode.com/problems/number-of-matching-subsequences/	\N	\N	\N	\N	12	2022-08-17 00:43:21.046738	2022-08-17 00:43:21.046738
6bb47cef-b9aa-4f15-b02d-3fb1fe9f8fe3	Crawler Log Folder	https://leetcode.com/problems/valid-parentheses	\N	\N	\N	\N	13	2022-08-17 00:43:21.054336	2022-08-17 00:43:21.054336
18a6e5d3-1e34-4fe0-8c4c-4fd0d2af1e67	Best Meeting Spot	https://leetcode.com/problems/best-meeting-point/	\N	\N	\N	\N	14	2022-08-17 00:43:21.062084	2022-08-17 00:43:21.062084
1c12b4ee-0707-4085-a59c-bae4c12ca02d	Word Dictionary	https://leetcode.com/problems/design-add-and-search-words-data-structure/	\N	\N	\N	\N	15	2022-08-17 00:43:21.070547	2022-08-17 00:43:21.070547
5cd67554-da45-4c15-a96c-24ede25927a3	Search Insert Position	https://leetcode.com/problems/search-insert-position/	\N	\N	\N	\N	16	2022-08-17 00:43:21.078878	2022-08-17 00:43:21.078878
2079145e-33f9-4e4e-9e85-b725dfe4737d	Write an algorithm to re-order a linked-list	https://leetcode.com/problems/reorder-list/	\N	\N	\N	\N	17	2022-08-17 00:43:21.086966	2022-08-17 00:43:21.086966
e9c7dc74-2326-4fc0-bd29-9561dfcafc01	Number of Good Pairs	https://leetcode.com/problems/number-of-good-pairs/	\N	\N	\N	\N	18	2022-08-17 00:43:21.094734	2022-08-17 00:43:21.094734
ad63967b-d296-4451-b940-8d6ce2d0242e	Count Sorted Vowel Strings	https://leetcode.com/problems/count-sorted-vowel-strings/	\N	\N	\N	\N	19	2022-08-17 00:43:21.103612	2022-08-17 00:43:21.103612
be992270-456a-4b06-9e49-219e267d320b	Find if there are two numbers with a specific target sum	https://leetcode.com/problems/two-sum/	\N	\N	\N	\N	20	2022-08-17 00:43:21.113872	2022-08-17 00:43:21.113872
0a3e6fd3-9433-465d-a06a-3bc5ba54d02c	Sort strings in a sentence	https://leetcode.com/problems/sorting-the-sentence/	\N	\N	\N	\N	21	2022-08-17 00:43:21.122868	2022-08-17 00:43:21.122868
2cc53ff9-765d-4f81-add4-c6c4123d47f6	Check if Word Equals Summation of Two Words	https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/	\N	\N	\N	\N	22	2022-08-17 00:43:21.130696	2022-08-17 00:43:21.130696
6c884c0d-ed4e-4caf-b2a0-c0dd7e6234e9	Move Zeroes	https://leetcode.com/problems/nested-list-weight-sum/	\N	\N	\N	\N	23	2022-08-17 00:43:21.138629	2022-08-17 00:43:21.138629
0f8115e2-69bf-4913-ac1e-48e27177502c	Reverse a linked-list	https://leetcode.com/problems/reverse-linked-list/	\N	\N	\N	\N	24	2022-08-17 00:43:21.147687	2022-08-17 00:43:21.147687
2c88d97b-3404-4e8f-be6e-eb888bb5c280	Serialize and Deserialize Binary Tree	https://leetcode.com/problems/serialize-and-deserialize-binary-tree/	\N	\N	\N	\N	25	2022-08-17 00:43:21.155633	2022-08-17 00:43:21.155633
42ab0960-c9e3-4ff2-b4c6-a3f9df6cdd18	Find a valid format for an alien dictionary	https://leetcode.com/problems/verifying-an-alien-dictionary/	\N	\N	\N	\N	26	2022-08-17 00:43:21.163858	2022-08-17 00:43:21.163858
08eb474d-b2be-4024-a216-77f01b9bcf89	Last Stone Weight	https://leetcode.com/problems/last-stone-weight/	\N	\N	\N	\N	27	2022-08-17 00:43:21.172774	2022-08-17 00:43:21.172774
053dd021-948b-44ea-a8d6-e0e6537eb89b	Find a valid course schedule	https://leetcode.com/problems/course-schedule/	\N	\N	\N	\N	28	2022-08-17 00:43:21.180853	2022-08-17 00:43:21.180853
5bf13854-bf5e-4d4c-a89f-ea5cbcc7301c	Sort Array By Parity	https://leetcode.com/problems/sort-array-by-parity/	\N	\N	\N	\N	29	2022-08-17 00:43:21.189197	2022-08-17 00:43:21.189197
a712eaff-2afc-4201-81e7-081e907ac231	Reconstruct Itinerary	https://leetcode.com/problems/reconstruct-itinerary/	\N	\N	\N	\N	30	2022-08-17 00:43:21.197431	2022-08-17 00:43:21.197431
62265019-6a9b-4fa0-94f4-802cf20b0e44	Construct Binary Tree from Preorder and Inorder Traversal	https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/	\N	\N	\N	\N	31	2022-08-17 00:43:21.207825	2022-08-17 00:43:21.207825
d268bf04-4ccd-498d-99a9-a6694d932abe	Number Of Islands	https://leetcode.com/problems/number-of-islands/	\N	\N	\N	\N	32	2022-08-17 00:43:21.2152	2022-08-17 00:43:21.2152
2dc1c761-f9e6-4a2a-bc9b-d14505319fb9	Shuffle String	https://leetcode.com/problems/shuffle-string/	\N	\N	\N	\N	33	2022-08-17 00:43:21.223816	2022-08-17 00:43:21.223816
f4c7ed74-2c6b-414d-bf74-a96b6161912b	Two Sum Less Than K	https://leetcode.com/problems/two-sum-less-than-k/	\N	\N	\N	\N	34	2022-08-17 00:43:21.231767	2022-08-17 00:43:21.231767
22666a17-fe03-4394-87b4-ce04ca0d29ab	Find the number that occurs once	https://leetcode.com/problems/single-number/	\N	\N	\N	\N	35	2022-08-17 00:43:21.239671	2022-08-17 00:43:21.239671
4551911b-8517-4c28-8439-5aecdc8c5357	Fizz Buzz	https://leetcode.com/problems/fizz-buzz/	\N	\N	\N	\N	36	2022-08-17 00:43:21.24779	2022-08-17 00:43:21.24779
26394ca7-ef45-4d00-9226-cafc4c5e0380	N-th Tribonacci Number	https://leetcode.com/problems/n-th-tribonacci-number/	\N	\N	\N	\N	37	2022-08-17 00:43:21.255824	2022-08-17 00:43:21.255824
831a8867-a6a1-4f9b-927c-bc775b33c548	Strobogrammatic Number	https://leetcode.com/problems/strobogrammatic-number/	\N	\N	\N	\N	38	2022-08-17 00:43:21.263482	2022-08-17 00:43:21.263482
4d73c5eb-697a-4170-96ee-f113fd185c0c	Merge Two Binary Trees	https://leetcode.com/problems/merge-two-binary-trees/	\N	\N	\N	\N	39	2022-08-17 00:43:21.272381	2022-08-17 00:43:21.272381
4fd9b039-1303-4a06-8256-5d5b224d5294	Min Stack	https://leetcode.com/problems/min-stack/	\N	\N	\N	\N	40	2022-08-17 00:43:21.279949	2022-08-17 00:43:21.279949
304d4592-d4c4-4ba4-9cf2-edff4afa8614	Design an algorithm to encode a list of strings to a string	https://leetcode.com/problems/encode-and-decode-strings/	\N	\N	\N	\N	41	2022-08-17 00:43:21.289025	2022-08-17 00:43:21.289025
4c197077-574f-4e22-9834-eeeb8aaba034	Two Sum III - Data structure design	https://leetcode.com/problems/two-sum-iii-data-structure-design/	\N	\N	\N	\N	42	2022-08-17 00:43:21.297078	2022-08-17 00:43:21.297078
394ea21e-d46c-4581-9d33-e5076d990fc1	Tweet Counts Per Frequency	https://leetcode.com/problems/tweet-counts-per-frequency/	\N	\N	\N	\N	43	2022-08-17 00:43:21.305298	2022-08-17 00:43:21.305298
c2ac40d4-8251-4add-8704-c56d4ab270fa	Longest Uncommon Subsequence I	https://leetcode.com/problems/longest-uncommon-subsequence-i/	\N	\N	\N	\N	44	2022-08-17 00:43:21.31482	2022-08-17 00:43:21.31482
9b13262d-a52a-4bbf-afb4-0f231a115423	Rotated Digits	https://leetcode.com/problems/rotated-digits/	\N	\N	\N	\N	45	2022-08-17 00:43:21.322754	2022-08-17 00:43:21.322754
801233ec-dfc7-4642-b663-2668fcfe6349	Binary Tree To Doubly Linked List	https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/	\N	\N	\N	\N	46	2022-08-17 00:43:21.33081	2022-08-17 00:43:21.33081
c99effa2-5105-442f-ac1f-b9c1f8330693	Egg Drop	https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/	\N	\N	\N	\N	47	2022-08-17 00:43:21.338958	2022-08-17 00:43:21.338958
3c6d5c80-cb48-49e7-b20f-5250a78b24e2	Maximum Swap	https://leetcode.com/problems/maximum-swap/	\N	\N	\N	\N	48	2022-08-17 00:43:21.347112	2022-08-17 00:43:21.347112
c5e1e24a-af0a-42ed-8f25-155a9868fca4	Verifying an Alien Dictionary	https://leetcode.com/problems/verifying-an-alien-dictionary/	\N	\N	\N	\N	49	2022-08-17 00:43:21.355976	2022-08-17 00:43:21.355976
93e88577-83d0-4248-afdd-bfc32a08edde	Find the missing number in an array	https://leetcode.com/problems/missing-number/	\N	\N	\N	\N	50	2022-08-17 00:43:21.364462	2022-08-17 00:43:21.364462
a81f31a0-e6cc-49aa-be59-19c030b464f1	Sort By Increasing Frequency	https://leetcode.com/problems/sort-array-by-increasing-frequency/	\N	\N	\N	\N	51	2022-08-17 00:43:21.373637	2022-08-17 00:43:21.373637
9bea60ea-cd10-4d29-90b8-106d7bc38790	Find if a graph is a valid tree	https://leetcode.com/problems/graph-valid-tree/	\N	\N	\N	\N	52	2022-08-17 00:43:21.381172	2022-08-17 00:43:21.381172
79f591c0-b8c7-41bb-8b5c-68046c2d59bd	Kids With the Greatest Number of Candies	https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/	\N	\N	\N	\N	53	2022-08-17 00:43:21.389213	2022-08-17 00:43:21.389213
ebc1e8f6-eb9f-45cb-9cb9-6d53ea6d3e8c	Implement Heap Sort	https://leetcode.com/problems/sort-an-array/	\N	\N	\N	\N	54	2022-08-17 00:43:21.396535	2022-08-17 00:43:21.396535
83110de3-9644-4da7-ac0f-542d1459d6c7	Minimum Path Sum	https://leetcode.com/problems/minimum-path-sum/	\N	\N	\N	\N	55	2022-08-17 00:43:21.404404	2022-08-17 00:43:21.404404
01e23605-9af5-4f67-8d9e-684c46208d06	Goat Latin	https://leetcode.com/problems/goat-latin/	\N	\N	\N	\N	56	2022-08-17 00:43:21.41225	2022-08-17 00:43:21.41225
e94a118c-5926-4cd6-80fd-6383528c4faa	Palindrome Permutation	https://leetcode.com/problems/palindrome-permutation/	\N	\N	\N	\N	57	2022-08-17 00:43:21.41995	2022-08-17 00:43:21.41995
967f0c8b-4cd5-46c2-9f70-fd6c8068090d	Find the lowest common ancestor of two nodes in a subtree	https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/	\N	\N	\N	\N	58	2022-08-17 00:43:21.428117	2022-08-17 00:43:21.428117
d6749ccc-9828-49cf-9810-087180b87dcd	Find Center Of Graph	https://leetcode.com/problems/find-center-of-star-graph/	\N	\N	\N	\N	59	2022-08-17 00:43:21.436264	2022-08-17 00:43:21.436264
84df2abf-12b5-4e9d-9803-f9bfa3505e2c	Find the Kth smallest element in a binary search tree	https://leetcode.com/problems/kth-smallest-element-in-a-bst/	\N	\N	\N	\N	60	2022-08-17 00:43:21.445295	2022-08-17 00:43:21.445295
94002c52-393b-40a3-89b8-fefc61d66d99	Find the missing ranges from an array	https://leetcode.com/problems/missing-ranges/	\N	\N	\N	\N	61	2022-08-17 00:43:21.453296	2022-08-17 00:43:21.453296
2961d3cb-5512-4339-97c0-d6ebc26c58a1	Find Minimum in Rotated Sorted Array	https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/	\N	\N	\N	\N	62	2022-08-17 00:43:21.468488	2022-08-17 00:43:21.468488
ffe04af7-b70a-4e5e-9038-6dfde40e723c	Shortest Completing Word	https://leetcode.com/problems/shortest-completing-word/	\N	\N	\N	\N	63	2022-08-17 00:43:21.477257	2022-08-17 00:43:21.477257
179aa8f7-8cb5-4e72-acfe-f3b3e04e4e5f	Robot Bounded In Circle	https://leetcode.com/problems/robot-bounded-in-circle/	\N	\N	\N	\N	64	2022-08-17 00:43:21.485719	2022-08-17 00:43:21.485719
b6af97a3-5d49-4aa2-9dc4-879706eb9906	Unique Paths	https://leetcode.com/problems/unique-paths/	\N	\N	\N	\N	65	2022-08-17 00:43:21.493537	2022-08-17 00:43:21.493537
27d57963-0ff8-4210-8521-9fb42a1e843a	Valid Palindrome	https://leetcode.com/problems/valid-palindrome/	\N	\N	\N	\N	66	2022-08-17 00:43:21.501727	2022-08-17 00:43:21.501727
57723985-68f6-48ea-86bf-7d28104e35a0	Find the number of connected components in a graph	https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/	\N	\N	\N	\N	67	2022-08-17 00:43:21.51006	2022-08-17 00:43:21.51006
aafc0041-a62b-4eaa-9918-0e388325ffe4	Kth Smallest Element in a Sorted Matrix	https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/	\N	\N	\N	\N	68	2022-08-17 00:43:21.518193	2022-08-17 00:43:21.518193
653f9761-caab-40e2-9a3a-2871c7d0510c	Parantheses Generator	https://leetcode.com/problems/generate-parentheses/	\N	\N	\N	\N	69	2022-08-17 00:43:21.52602	2022-08-17 00:43:21.52602
274baaf4-c5ea-4830-ba67-b85699e5891b	Palindrome Linked List	https://leetcode.com/problems/palindrome-linked-list/	\N	\N	\N	\N	70	2022-08-17 00:43:21.534459	2022-08-17 00:43:21.534459
f85b09b6-7569-4069-9b9f-581ac174a8f7	Print the level order traversal of a binary tree	https://leetcode.com/problems/binary-tree-level-order-traversal/	\N	\N	\N	\N	71	2022-08-17 00:43:21.542989	2022-08-17 00:43:21.542989
9ce141d3-c1c0-4b39-a4bf-77d5e984e68f	Excel Sheet Column Title	https://leetcode.com/problems/excel-sheet-column-title/	\N	\N	\N	\N	72	2022-08-17 00:43:21.551243	2022-08-17 00:43:21.551243
d0ac98fc-62be-4f34-ad51-f21d9186feea	Find If Path Exists	https://leetcode.com/problems/find-if-path-exists-in-graph/	\N	\N	\N	\N	73	2022-08-17 00:43:21.559999	2022-08-17 00:43:21.559999
d9e2fe50-60ee-4bc2-b210-608ae5a8f8e6	Binary Tree Tilt	https://leetcode.com/problems/binary-tree-tilt/	\N	\N	\N	\N	74	2022-08-17 00:43:21.575117	2022-08-17 00:43:21.575117
60bdc790-8c7b-4ce3-8695-a6cab34b6910	Palindromic Substrings	https://leetcode.com/problems/palindromic-substrings/	\N	\N	\N	\N	75	2022-08-17 00:43:21.58445	2022-08-17 00:43:21.58445
a46f0fee-acb6-4de3-94b3-f646f78a048f	Uncommon Words from Two Sentences	https://leetcode.com/problems/uncommon-words-from-two-sentences/	\N	\N	\N	\N	76	2022-08-17 00:43:21.596463	2022-08-17 00:43:21.596463
c86a839a-ea3a-4366-be01-de0181449c35	Range Sum Query	https://leetcode.com/problems/range-sum-query-mutable/	\N	\N	\N	\N	77	2022-08-17 00:43:21.608527	2022-08-17 00:43:21.608527
ba98857e-c831-497d-9f0b-9ee6aae03a70	Count the number of 1 bits in a number	https://leetcode.com/problems/number-of-1-bits/	\N	\N	\N	\N	78	2022-08-17 00:43:21.620448	2022-08-17 00:43:21.620448
bab4ba80-c977-4215-8844-0f9b9b344886	Implement Insert Sort	https://leetcode.com/problems/sort-an-array/	\N	\N	\N	\N	79	2022-08-17 00:43:21.629193	2022-08-17 00:43:21.629193
6cf0a7f7-6de4-4af9-ae83-1f56c6237548	Count number of jewels and stones in a collection	https://leetcode.com/problems/jewels-and-stones/	\N	\N	\N	\N	80	2022-08-17 00:43:21.639202	2022-08-17 00:43:21.639202
9823af67-f97a-48d2-8a9d-0187e730f882	Underground System	https://leetcode.com/problems/design-underground-system/	\N	\N	\N	\N	81	2022-08-17 00:43:21.654224	2022-08-17 00:43:21.654224
1086dbb2-f821-4d66-87b6-062e3f2faf83	Climbing Stairs	https://leetcode.com/problems/climbing-stairs/	\N	\N	\N	\N	82	2022-08-17 00:43:21.662531	2022-08-17 00:43:21.662531
368616c7-d8eb-4eff-9144-76e7b4c164ce	Binary Tree Maximum Path Sum	https://leetcode.com/problems/binary-tree-maximum-path-sum/	\N	\N	\N	\N	83	2022-08-17 00:43:21.670456	2022-08-17 00:43:21.670456
79ad4957-1ef8-499b-b727-4ddb57aa6410	All Paths From Source to Target	https://leetcode.com/problems/all-paths-from-source-to-target/	\N	\N	\N	\N	84	2022-08-17 00:43:21.678067	2022-08-17 00:43:21.678067
0757ffa0-525d-4fcf-9dcf-26e32b97eeac	Text Justification	https://leetcode.com/problems/text-justification/	\N	\N	\N	\N	85	2022-08-17 00:43:21.686192	2022-08-17 00:43:21.686192
b4d68584-a81a-4e1f-bfd7-74d5ddbfa8fd	Find the middle of a linked-list	https://leetcode.com/problems/middle-of-the-linked-list/	\N	\N	\N	\N	86	2022-08-17 00:43:21.695594	2022-08-17 00:43:21.695594
8bdabcf3-e325-4521-b6e4-e798e6c2f010	Asteroid Collision	https://leetcode.com/problems/asteroid-collision/	\N	\N	\N	\N	87	2022-08-17 00:43:21.703719	2022-08-17 00:43:21.703719
be744bb1-970b-4872-b45c-ac2a2803186d	Find non-overlapping meeting rooms	https://leetcode.com/problems/non-overlapping-intervals/	\N	\N	\N	\N	88	2022-08-17 00:43:21.712139	2022-08-17 00:43:21.712139
7f8f8c4c-9a55-412b-b481-c9a4921b2fa9	Print the level order traversal of a tree	https://leetcode.com/problems/binary-tree-level-order-traversal/	\N	\N	\N	\N	89	2022-08-17 00:43:21.719998	2022-08-17 00:43:21.719998
fb5d8862-36eb-472d-9412-86636562de3a	Make The String Great	https://leetcode.com/problems/make-the-string-great/	\N	\N	\N	\N	90	2022-08-17 00:43:21.728034	2022-08-17 00:43:21.728034
5d1ac649-a7d8-41f5-b450-2cd566f97a16	Top K Frequent Words	https://leetcode.com/problems/top-k-frequent-words/	\N	\N	\N	\N	91	2022-08-17 00:43:21.735985	2022-08-17 00:43:21.735985
0884bfc7-1737-4502-81c7-de2aff71fd93	Keys And Rooms	https://leetcode.com/problems/keys-and-rooms/	\N	\N	\N	\N	92	2022-08-17 00:43:21.744523	2022-08-17 00:43:21.744523
40fd08c5-2c92-4fa3-9242-5d4a92915882	Validate a binary search tree	https://leetcode.com/problems/validate-binary-search-tree/	\N	\N	\N	\N	93	2022-08-17 00:43:21.763053	2022-08-17 00:43:21.763053
a2841002-e69c-4a94-b7a3-223426c1372d	Validate a binary search tree	https://leetcode.com/problems/validate-binary-search-tree/	\N	\N	\N	\N	94	2022-08-17 00:43:21.773352	2022-08-17 00:43:21.773352
b0dae42c-ca2c-4e67-9f6a-75bb8140d4f7	Make Two Arrays Equal by Reversing Sub-arrays	https://leetcode.com/problems/merge-sorted-array/	\N	\N	\N	\N	95	2022-08-17 00:43:21.781724	2022-08-17 00:43:21.781724
8fb2b4ba-0067-4fe1-bad1-6e92bad46fd4	Word Pattern	https://leetcode.com/problems/word-pattern/	\N	\N	\N	\N	96	2022-08-17 00:43:21.789531	2022-08-17 00:43:21.789531
f5fb5668-d302-411b-92a1-bbf3cf48718a	Implement Bubble Sort	https://leetcode.com/problems/sort-an-array/	\N	\N	\N	\N	97	2022-08-17 00:43:21.79735	2022-08-17 00:43:21.79735
0c34f2ee-6a70-4569-b5bc-f23238407a79	Max Nesting Depth	https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses	\N	\N	\N	\N	98	2022-08-17 00:43:21.805379	2022-08-17 00:43:21.805379
0e5e8b44-05c5-4d0f-a632-bc0c063ed7cb	Calculate the minimum network delay	https://leetcode.com/problems/network-delay-time/	\N	\N	\N	\N	99	2022-08-17 00:43:21.813809	2022-08-17 00:43:21.813809
02f6c67f-0ce9-4b5a-9318-b2117afc4f7b	Valid Parantheses	https://leetcode.com/problems/valid-parentheses/	\N	\N	\N	\N	100	2022-08-17 00:43:21.821572	2022-08-17 00:43:21.821572
0531fd70-6341-477c-ad7e-f1004f9aa016	Find the fixed point in an array	https://leetcode.com/problems/fixed-point/	\N	\N	\N	\N	101	2022-08-17 00:43:21.829759	2022-08-17 00:43:21.829759
fbe40c40-0bf3-4aec-82ca-f62ebd6c8739	All Paths from Source Lead to Destination	https://leetcode.com/problems/all-paths-from-source-lead-to-destination/	\N	\N	\N	\N	102	2022-08-17 00:43:21.837864	2022-08-17 00:43:21.837864
fe0cb9f7-e781-47a1-ba5b-f76079ebb58d	Basic Calculator	https://leetcode.com/problems/basic-calculator-ii/	\N	\N	\N	\N	103	2022-08-17 00:43:21.845909	2022-08-17 00:43:21.845909
fa52574d-15bd-48b8-96c9-4dd155ee4798	Minimum Vertices To Nodes	https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/	\N	\N	\N	\N	104	2022-08-17 00:43:21.853869	2022-08-17 00:43:21.853869
d41586d6-a5ce-4d62-a250-f6dbe693e5a8	Average of Levels in Binary Tree	https://leetcode.com/problems/average-of-levels-in-binary-tree/	\N	\N	\N	\N	105	2022-08-17 00:43:21.862232	2022-08-17 00:43:21.862232
fc99a083-35d1-4da9-a3a6-b6beef1694c1	Find the intersection of two arrays	https://leetcode.com/problems/intersection-of-two-arrays/	\N	\N	\N	\N	106	2022-08-17 00:43:21.871416	2022-08-17 00:43:21.871416
4f14eeaf-32e0-421b-9f45-77f611d01919	Accounts Merge	https://leetcode.com/problems/accounts-merge/	\N	\N	\N	\N	107	2022-08-17 00:43:21.879652	2022-08-17 00:43:21.879652
600d788e-b0fa-432a-ae81-51d883a708bd	Monotonic Array	https://leetcode.com/problems/monotonic-array/	\N	\N	\N	\N	108	2022-08-17 00:43:21.887311	2022-08-17 00:43:21.887311
85fc93cd-0b31-4976-8552-c813f517cdc9	Max Stack	https://leetcode.com/problems/max-stack/	\N	\N	\N	\N	109	2022-08-17 00:43:21.896304	2022-08-17 00:43:21.896304
bea45591-c2f8-4a99-bc59-b73c735070e6	Add to Array-Form of Integer	https://leetcode.com/problems/add-to-array-form-of-integer/	\N	\N	\N	\N	110	2022-08-17 00:43:21.904416	2022-08-17 00:43:21.904416
ad9352da-1648-45a9-a8a3-85ca8f6488c2	Find the container with the maximum water	https://leetcode.com/problems/container-with-most-water/	\N	\N	\N	\N	111	2022-08-17 00:43:21.91225	2022-08-17 00:43:21.91225
ee70905c-8b39-46fe-bf2f-743f570b5574	Check If Two Expression Trees are Equivalent	https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/	\N	\N	\N	\N	112	2022-08-17 00:43:21.920209	2022-08-17 00:43:21.920209
c8a21622-57fe-4cc1-907c-1b0cb54065ac	Flood Fill	https://leetcode.com/problems/flood-fill/	\N	\N	\N	\N	113	2022-08-17 00:43:21.927722	2022-08-17 00:43:21.927722
53e08ca9-fc48-4af4-a15c-453f57726697	Nested Iterator	https://leetcode.com/problems/flatten-nested-list-iterator/	\N	\N	\N	\N	114	2022-08-17 00:43:21.935964	2022-08-17 00:43:21.935964
6bbc7d05-7c4f-4a0a-ae58-cd7b9831d2da	Add Two Numbers	https://leetcode.com/problems/add-two-numbers/	\N	\N	\N	\N	115	2022-08-17 00:43:21.943562	2022-08-17 00:43:21.943562
a8e371f2-2888-40da-81f9-a16acfa96c34	Decode Ways	https://leetcode.com/problems/decode-ways/	\N	\N	\N	\N	116	2022-08-17 00:43:21.951499	2022-08-17 00:43:21.951499
14b3cd0d-b520-4a9a-8212-9ac9eaa6a8fc	Shifting Letters	https://leetcode.com/problems/shifting-letters/	\N	\N	\N	\N	117	2022-08-17 00:43:21.959291	2022-08-17 00:43:21.959291
0aad2620-999c-470a-8c17-19abc0b7f251	Decompress Run-Length Encoded List	https://leetcode.com/problems/decompress-run-length-encoded-list/	\N	\N	\N	\N	118	2022-08-17 00:43:21.967424	2022-08-17 00:43:21.967424
8eda275d-0fa2-4347-898b-4a328ad84418	Minimum Depth of Binary Tree	https://leetcode.com/problems/minimum-depth-of-binary-tree/	\N	\N	\N	\N	119	2022-08-17 00:43:21.976365	2022-08-17 00:43:21.976365
dfa9525b-e6d6-408a-b6ca-32a24113c091	Make String Valid	https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/	\N	\N	\N	\N	120	2022-08-17 00:43:21.985283	2022-08-17 00:43:21.985283
6cf11880-46f3-4038-956a-1b8f659f0ef4	Best Time To Buy And Sell Stocks	https://leetcode.com/problems/best-time-to-buy-and-sell-stock/	\N	\N	\N	\N	121	2022-08-17 00:43:21.994566	2022-08-17 00:43:21.994566
f61dddcd-ad39-4b24-ae5a-2c1480e341f4	Find the sum of the maximum subarray with consecutive elements	https://leetcode.com/problems/maximum-subarray/	\N	\N	\N	\N	122	2022-08-17 00:43:22.003092	2022-08-17 00:43:22.003092
47759934-6cac-47e8-9a01-5a7ef9772462	Longest Continuous Increasing Subsequence	https://leetcode.com/problems/longest-continuous-increasing-subsequence/	\N	\N	\N	\N	123	2022-08-17 00:43:22.010625	2022-08-17 00:43:22.010625
6760401e-ef80-4216-a4c7-0e43f69c63e5	Merge N Sorted Arrays	https://leetcode.com/problems/merge-sorted-array/	\N	\N	\N	\N	124	2022-08-17 00:43:22.018105	2022-08-17 00:43:22.018105
4d184e05-d665-493f-9af6-82f57bdce40d	Check if a duplicate exists in an array in O(N)	https://leetcode.com/problems/contains-duplicate/	\N	\N	\N	\N	125	2022-08-17 00:43:22.026185	2022-08-17 00:43:22.026185
36c50740-a422-4c02-9c65-2d61e6ce91e9	Logger Rate Limiter	https://leetcode.com/problems/logger-rate-limiter/	\N	\N	\N	\N	126	2022-08-17 00:43:22.033769	2022-08-17 00:43:22.033769
8c7c7272-8844-4791-af23-bc2d33e113c2	Serialize and Deserialize Binary Tree	https://leetcode.com/problems/serialize-and-deserialize-binary-tree/	\N	\N	\N	\N	127	2022-08-17 00:43:22.041839	2022-08-17 00:43:22.041839
12f2579c-7ceb-4283-a32c-f6d67685a032	Find Town Judge	https://leetcode.com/problems/find-the-town-judge/	\N	\N	\N	\N	128	2022-08-17 00:43:22.049518	2022-08-17 00:43:22.049518
4040f5b4-b6d5-4061-be85-8caf9bf28bd0	Ransom Note	https://leetcode.com/problems/ransom-note/	\N	\N	\N	\N	129	2022-08-17 00:43:22.056946	2022-08-17 00:43:22.056946
97ae77ea-1550-4d6a-b89a-eaf21c19a054	Find the third maximum element in an array	https://leetcode.com/problems/third-maximum-number/	\N	\N	\N	\N	130	2022-08-17 00:43:22.064959	2022-08-17 00:43:22.064959
65751e16-d750-4d2c-b098-e58213e577c1	Create a target array in a pre-specified format	https://leetcode.com/problems/create-target-array-in-the-given-order/	\N	\N	\N	\N	131	2022-08-17 00:43:22.073248	2022-08-17 00:43:22.073248
9ce957cd-6339-4d1a-8e7c-8dbbf0190d9a	H-Index Calculator	https://leetcode.com/problems/h-index/	\N	\N	\N	\N	132	2022-08-17 00:43:22.081832	2022-08-17 00:43:22.081832
1f0ecadf-0641-4189-ac6b-7338aa19a470	Combinations With Duplicates	https://leetcode.com/problems/permutations-ii/	\N	\N	\N	\N	133	2022-08-17 00:43:22.089556	2022-08-17 00:43:22.089556
906ae37c-ba17-42a9-bc55-07053dd2f6ed	Reorder Data in Log Files	https://leetcode.com/problems/reorder-data-in-log-files/	\N	\N	\N	\N	134	2022-08-17 00:43:22.097675	2022-08-17 00:43:22.097675
4ffe84f8-287f-4034-a8db-7a56dc09721f	Convert Sorted Array to Binary Search Tree	https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/	\N	\N	\N	\N	135	2022-08-17 00:43:22.106291	2022-08-17 00:43:22.106291
438d34db-f2a2-45ee-826c-89e580d2afc8	Invert a binary tree	https://leetcode.com/problems/invert-binary-tree/	\N	\N	\N	\N	136	2022-08-17 00:43:22.115985	2022-08-17 00:43:22.115985
3bf460a8-0547-4b0b-bc29-61f3b20bc54c	Minimum Difference Between Largest and Smallest Value in Three Moves	https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/	\N	\N	\N	\N	137	2022-08-17 00:43:22.125052	2022-08-17 00:43:22.125052
f231fb6e-8517-462e-ba9e-af9c82389be6	Binary Watch	https://leetcode.com/problems/binary-watch/	\N	\N	\N	\N	138	2022-08-17 00:43:22.133528	2022-08-17 00:43:22.133528
71432ddb-d670-42d2-9433-ff1e693e7e81	Find a number in the fibonacci sequence	https://leetcode.com/problems/fibonacci-number/	\N	\N	\N	\N	139	2022-08-17 00:43:22.141325	2022-08-17 00:43:22.141325
4c052192-f1be-4531-8fad-10fd587119b8	Pascal Triangle	https://leetcode.com/problems/pascals-triangle/	\N	\N	\N	\N	140	2022-08-17 00:43:22.149483	2022-08-17 00:43:22.149483
a1400ddb-0c7e-44cf-9c36-23c2c444a040	Rotate Array	https://leetcode.com/problems/rotate-array/	\N	\N	\N	\N	141	2022-08-17 00:43:22.158324	2022-08-17 00:43:22.158324
5a2b1ea1-71b4-4b9e-88cc-fb258cbbd521	Combinations	https://leetcode.com/problems/combinations/	\N	\N	\N	\N	142	2022-08-17 00:43:22.16604	2022-08-17 00:43:22.16604
e1ef7380-9e69-46d9-8fd2-54b7050d7351	Palindromic Partitioning	https://leetcode.com/problems/palindrome-partitioning/	\N	\N	\N	\N	143	2022-08-17 00:43:22.174688	2022-08-17 00:43:22.174688
71639cde-cad0-4d5c-be59-d9ddeb913e18	Employee Importance	https://leetcode.com/problems/employee-importance/	\N	\N	\N	\N	144	2022-08-17 00:43:22.182645	2022-08-17 00:43:22.182645
82d4283e-797a-4eb6-88ef-24fdc0813d54	Find spiral order in a matrix	https://leetcode.com/problems/spiral-matrix/	\N	\N	\N	\N	145	2022-08-17 00:43:22.19082	2022-08-17 00:43:22.19082
a7adbd02-fe8a-4561-bb62-099128d75487	Insert new interval in non-overlapping intervals	https://leetcode.com/problems/insert-interval/	\N	\N	\N	\N	146	2022-08-17 00:43:22.199193	2022-08-17 00:43:22.199193
5ad1f91f-8b4c-4135-a59f-aa333a07e2bc	Reverse String	https://leetcode.com/problems/reverse-string/	\N	\N	\N	\N	147	2022-08-17 00:43:22.208264	2022-08-17 00:43:22.208264
e3978a89-118c-489f-a927-f37035bcf168	Parallel Courses	https://leetcode.com/problems/parallel-courses/	\N	\N	\N	\N	148	2022-08-17 00:43:22.21626	2022-08-17 00:43:22.21626
7a95192d-bc46-450f-a6b6-904498494ad7	Word Break	https://leetcode.com/problems/word-break/	\N	\N	\N	\N	149	2022-08-17 00:43:22.224696	2022-08-17 00:43:22.224696
9a13a8ea-8a94-4278-9101-a431f3698de9	Implement strStr()	https://leetcode.com/problems/implement-strstr/	\N	\N	\N	\N	150	2022-08-17 00:43:22.232635	2022-08-17 00:43:22.232635
47ec5c05-fb56-426e-bebb-13f786d78460	Implement Queue using Stacks	https://leetcode.com/problems/implement-queue-using-stacks/	\N	\N	\N	\N	151	2022-08-17 00:43:22.241427	2022-08-17 00:43:22.241427
f85ce7eb-465f-4d93-bf39-7154c087d28b	Top K Frequent Elements	https://leetcode.com/problems/top-k-frequent-elements/	\N	\N	\N	\N	152	2022-08-17 00:43:22.25128	2022-08-17 00:43:22.25128
02309a93-1974-4345-a438-688857a9b543	Remove vowels from a string	https://leetcode.com/problems/remove-vowels-from-a-string/	\N	\N	\N	\N	153	2022-08-17 00:43:22.259288	2022-08-17 00:43:22.259288
68b1b82b-3903-4284-905b-f46a3ed83000	Balanced Binary Tree	https://leetcode.com/problems/balanced-binary-tree/	\N	\N	\N	\N	154	2022-08-17 00:43:22.267156	2022-08-17 00:43:22.267156
0e3c3308-37d2-44ab-b82f-dfb743cae3e1	Sort Characters By Frequency	https://leetcode.com/problems/sort-characters-by-frequency/	\N	\N	\N	\N	155	2022-08-17 00:43:22.282869	2022-08-17 00:43:22.282869
e9042ae0-521c-4073-bb94-57c230493ba9	Design a SkipList	https://leetcode.com/problems/design-skiplist/	\N	\N	\N	\N	156	2022-08-17 00:43:22.29539	2022-08-17 00:43:22.29539
8232aa91-e95e-4833-ad17-ddc167de4ae4	Max Area Of Island	https://leetcode.com/problems/max-area-of-island/	\N	\N	\N	\N	157	2022-08-17 00:43:22.303662	2022-08-17 00:43:22.303662
2a04d77c-03d6-496f-8197-4e874ae92c3a	Design an algorithm to sort colors in a collection	https://leetcode.com/problems/sort-colors/	\N	\N	\N	\N	158	2022-08-17 00:43:22.312199	2022-08-17 00:43:22.312199
8d2e4c09-7d0e-4b67-9ae8-cea6f71f11e3	Letter Case Permutation	https://leetcode.com/problems/letter-case-permutation/	\N	\N	\N	\N	159	2022-08-17 00:43:22.320964	2022-08-17 00:43:22.320964
d442e800-9320-49a5-9fe0-877f4465bdab	Is Subsequence	https://leetcode.com/problems/is-subsequence/	\N	\N	\N	\N	160	2022-08-17 00:43:22.329747	2022-08-17 00:43:22.329747
19825857-373e-48ba-92f4-cbf4d170feca	Add Binary	https://leetcode.com/problems/add-binary/	\N	\N	\N	\N	161	2022-08-17 00:43:22.340398	2022-08-17 00:43:22.340398
53d861f6-95bf-4523-8f8c-39811e0e83c6	Group Anagrams	https://leetcode.com/problems/group-anagrams/	\N	\N	\N	\N	162	2022-08-17 00:43:22.347984	2022-08-17 00:43:22.347984
2dec5a23-ce4d-4361-aa55-cf90bb52296e	Add two sparse vectors	https://leetcode.com/problems/dot-product-of-two-sparse-vectors/	\N	\N	\N	\N	163	2022-08-17 00:43:22.356008	2022-08-17 00:43:22.356008
e7b5fdb1-cf77-4279-8049-84ada6d60b30	Reverse a 32-bit integer	https://leetcode.com/problems/reverse-integer/	\N	\N	\N	\N	164	2022-08-17 00:43:22.364503	2022-08-17 00:43:22.364503
5ef2b19c-c271-4145-9e09-3b9780725a25	Robot Bounded In Circle	https://leetcode.com/problems/robot-bounded-in-circle/	\N	\N	\N	\N	165	2022-08-17 00:43:22.372855	2022-08-17 00:43:22.372855
c4d1199f-9c7f-412c-b4c4-8b5ec22695bc	Num Teams	https://leetcode.com/problems/count-number-of-teams/	\N	\N	\N	\N	166	2022-08-17 00:43:22.380743	2022-08-17 00:43:22.380743
f9755d35-5096-422e-9cd9-209f922f01c4	For each element of an array find the product except itself	https://leetcode.com/problems/product-of-array-except-self/	\N	\N	\N	\N	167	2022-08-17 00:43:22.388957	2022-08-17 00:43:22.388957
77dc00d2-65f9-451e-92bc-3a7c7c57a256	Partition Array Maximum Sum	https://leetcode.com/problems/partition-array-for-maximum-sum	\N	\N	\N	\N	168	2022-08-17 00:43:22.396749	2022-08-17 00:43:22.396749
4f25f462-c079-4a91-b5f6-478d500bef90	Decode String	https://leetcode.com/problems/decode-string/	\N	\N	\N	\N	169	2022-08-17 00:43:22.404449	2022-08-17 00:43:22.404449
67bba7ba-efe4-4279-b7b2-cdea7e7f1634	Reorder Routes to Make All Paths Lead to the City Zero	https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/	\N	\N	\N	\N	170	2022-08-17 00:43:22.412729	2022-08-17 00:43:22.412729
e8e520d3-62fb-4d6e-9451-dc87dad8af17	Longest Substring Without Repeating Characters	https://leetcode.com/problems/longest-substring-without-repeating-characters/	\N	\N	\N	\N	171	2022-08-17 00:43:22.420173	2022-08-17 00:43:22.420173
3665fb50-1355-47ca-92a1-a9eb36817303	Interval List Intersections	https://leetcode.com/problems/interval-list-intersections/	\N	\N	\N	\N	172	2022-08-17 00:43:22.428304	2022-08-17 00:43:22.428304
a44ea26b-cc93-4a90-9a90-ed833ca6daf2	Find the longest common prefix from an array of strings	https://leetcode.com/problems/longest-common-prefix/	\N	\N	\N	\N	173	2022-08-17 00:43:22.436377	2022-08-17 00:43:22.436377
be8cfdb4-2d76-4208-b038-052a1fae85cc	Find a happy substring from a given string	https://leetcode.com/problems/longest-happy-string/	\N	\N	\N	\N	174	2022-08-17 00:43:22.445105	2022-08-17 00:43:22.445105
219333cf-b2c6-4469-a0bc-368ecf51abb3	Merge k Sorted Lists	https://leetcode.com/problems/merge-k-sorted-lists/	\N	\N	\N	\N	175	2022-08-17 00:43:22.452996	2022-08-17 00:43:22.452996
d674a0c8-f2b3-44fa-ba54-59b94b11935c	Reorganize String	https://leetcode.com/problems/reorganize-string/	\N	\N	\N	\N	176	2022-08-17 00:43:22.460548	2022-08-17 00:43:22.460548
61356f4e-7b01-4f08-adfb-815b38f24f20	Add String	https://leetcode.com/problems/add-strings/	\N	\N	\N	\N	177	2022-08-17 00:43:22.468712	2022-08-17 00:43:22.468712
8c0ecaf4-fc57-4567-9dd9-ee05c548f607	Find the maximum depth of a binary tree	https://leetcode.com/problems/maximum-depth-of-binary-tree/	\N	\N	\N	\N	178	2022-08-17 00:43:22.476547	2022-08-17 00:43:22.476547
2941ac07-dcd4-4527-8113-51081099abdc	Validate if a tree is a subtree of another tree	https://leetcode.com/problems/subtree-of-another-tree/	\N	\N	\N	\N	179	2022-08-17 00:43:22.485692	2022-08-17 00:43:22.485692
00d3bfd0-7061-4b1b-9b70-d38599474c9a	Target Sum	https://leetcode.com/problems/target-sum/	\N	\N	\N	\N	180	2022-08-17 00:43:22.493357	2022-08-17 00:43:22.493357
e47003af-5311-4606-9e03-3ef294d2736a	Remove Duplicates from Sorted Array	https://leetcode.com/problems/remove-duplicates-from-sorted-array/	\N	\N	\N	\N	181	2022-08-17 00:43:22.501231	2022-08-17 00:43:22.501231
0a272da6-36e8-4676-8dd4-87b68146935b	Roman To Integer	https://leetcode.com/problems/roman-to-integer/	\N	\N	\N	\N	182	2022-08-17 00:43:22.509029	2022-08-17 00:43:22.509029
af60d486-49a2-4422-913e-9bf6872be956	Calculate a students top five average	https://leetcode.com/problems/high-five/	\N	\N	\N	\N	183	2022-08-17 00:43:22.516526	2022-08-17 00:43:22.516526
67906756-f001-47ef-ac94-50ff58f6785d	Compare two binary tress	https://leetcode.com/problems/same-tree/	\N	\N	\N	\N	184	2022-08-17 00:43:22.524145	2022-08-17 00:43:22.524145
a0172e9e-c358-4410-98bb-24f209f3b7e3	Tic Tac Toe	https://leetcode.com/problems/design-tic-tac-toe/	\N	\N	\N	\N	185	2022-08-17 00:43:22.531948	2022-08-17 00:43:22.531948
2d424e63-bb96-45b5-b1ab-d9e624c11ca8	Range Sum of a binary search tree	https://leetcode.com/problems/range-sum-of-bst/	\N	\N	\N	\N	186	2022-08-17 00:43:22.539659	2022-08-17 00:43:22.539659
8171fe93-f6ee-4426-9b63-56b4af99a9a3	Design an LRU Cache	https://leetcode.com/problems/lru-cache/	\N	\N	\N	\N	187	2022-08-17 00:43:22.547171	2022-08-17 00:43:22.547171
59da6e69-5969-468f-9ec9-61eba36f69b6	Design an Ordered Stream	https://leetcode.com/problems/design-an-ordered-stream/	\N	\N	\N	\N	188	2022-08-17 00:43:22.555458	2022-08-17 00:43:22.555458
33515f49-8867-46db-bb3b-ecf1d61be67f	Can Place Flowers	https://leetcode.com/problems/can-place-flowers/	\N	\N	\N	\N	189	2022-08-17 00:43:22.562879	2022-08-17 00:43:22.562879
fad082c2-f6a8-4191-b07c-d4741cca9c88	Decode a string with a specific encoding	https://leetcode.com/problems/decode-string/	\N	\N	\N	\N	190	2022-08-17 00:43:22.572952	2022-08-17 00:43:22.572952
db468a6e-3d56-4427-bde2-d1db3d8ad0d3	Insert into a Sorted Circular Linked List	https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/	\N	\N	\N	\N	191	2022-08-17 00:43:22.58177	2022-08-17 00:43:22.58177
cabe17fa-6def-43b4-b4c0-4b6b3eccf685	Vertical Order Traversal of a Binary Tree	https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/	\N	\N	\N	\N	192	2022-08-17 00:43:22.58972	2022-08-17 00:43:22.58972
3a6f25ee-d0ce-4219-9010-ba070ecd1026	Check if two strings are buddy strings	https://leetcode.com/problems/buddy-strings/	\N	\N	\N	\N	193	2022-08-17 00:43:22.597818	2022-08-17 00:43:22.597818
5ee7d405-4184-49ef-845c-caa8e91d32d8	Kth Largest Element in an Array	https://leetcode.com/problems/kth-largest-element-in-an-array/	\N	\N	\N	\N	194	2022-08-17 00:43:22.605484	2022-08-17 00:43:22.605484
b1cab007-f7bf-4e6d-9c3e-e98116097dd5	First Unique Character in a String	https://leetcode.com/problems/first-unique-character-in-a-string/	\N	\N	\N	\N	195	2022-08-17 00:43:22.614822	2022-08-17 00:43:22.614822
2302dd7a-d2ff-4761-a5b0-9a30644b1139	Find if there exists a valid course schedule	https://leetcode.com/problems/course-schedule/	\N	\N	\N	\N	196	2022-08-17 00:43:22.623147	2022-08-17 00:43:22.623147
167acb2e-20e3-4ebe-901b-288ffd883ef2	Kth Largest Element in a Stream	https://leetcode.com/problems/kth-largest-element-in-a-stream/	\N	\N	\N	\N	197	2022-08-17 00:43:22.632596	2022-08-17 00:43:22.632596
ba4ffff9-1138-480a-a568-8b26c3a86baa	Reverse the bits in a number	https://leetcode.com/problems/reverse-bits/	\N	\N	\N	\N	198	2022-08-17 00:43:22.64064	2022-08-17 00:43:22.64064
5a935e92-9948-41bb-a9ce-347243b071fc	Get Maximum in Generated Array	https://leetcode.com/problems/get-maximum-in-generated-array/	\N	\N	\N	\N	199	2022-08-17 00:43:22.648929	2022-08-17 00:43:22.648929
bd613b09-93e8-443e-969b-ca1762bed6d3	Detect a cycle in a linked-list	https://leetcode.com/problems/linked-list-cycle/	\N	\N	\N	\N	200	2022-08-17 00:43:22.657685	2022-08-17 00:43:22.657685
5d02a7ae-7618-4e7e-8c0d-fb1d59c237ab	Find if a path has a specific sum	https://leetcode.com/problems/path-sum/	\N	\N	\N	\N	201	2022-08-17 00:43:22.665292	2022-08-17 00:43:22.665292
dba2471e-eec5-41b8-b198-504061698eac	Implement Merge Sort	https://leetcode.com/problems/sort-an-array/	\N	\N	\N	\N	202	2022-08-17 00:43:22.67392	2022-08-17 00:43:22.67392
bc77c316-8c03-429f-805d-cfb516408007	Swap Nodes in Pairs	https://leetcode.com/problems/swap-nodes-in-pairs/	\N	\N	\N	\N	203	2022-08-17 00:43:22.682522	2022-08-17 00:43:22.682522
b9877af0-5457-458e-b6ec-5ade98e14961	Partition Equal Subset Sum	https://leetcode.com/problems/partition-equal-subset-sum/	\N	\N	\N	\N	204	2022-08-17 00:43:22.690708	2022-08-17 00:43:22.690708
fab7b139-1cae-4c17-ba85-61955f11594f	Subarray Sum Equals K	https://leetcode.com/problems/subarray-sum-equals-k/	\N	\N	\N	\N	205	2022-08-17 00:43:22.698555	2022-08-17 00:43:22.698555
ced0eeb2-8155-4871-b40f-b3da3ac17635	Farmland Groups	https://leetcode.com/problems/find-all-groups-of-farmland/	\N	\N	\N	\N	206	2022-08-17 00:43:22.706301	2022-08-17 00:43:22.706301
1c8f727e-82ef-439d-8e7d-0dfa241c0c59	Remove Invalid Parantheses	https://leetcode.com/problems/remove-invalid-parentheses/	\N	\N	\N	\N	207	2022-08-17 00:43:22.714094	2022-08-17 00:43:22.714094
2a539916-6602-4446-b873-4ac27d2a1602	Plus One	https://leetcode.com/problems/plus-one/	\N	\N	\N	\N	208	2022-08-17 00:43:22.721557	2022-08-17 00:43:22.721557
39451ce4-b86b-4c9a-8bdc-a1230e3eb205	Implement Binary Search	https://leetcode.com/problems/binary-search/	\N	\N	\N	\N	209	2022-08-17 00:43:22.729093	2022-08-17 00:43:22.729093
3c62c4cb-95f5-4a7e-9c72-9490c919c430	Find all collection of four elements that have sum equal to K	https://leetcode.com/problems/4sum/	\N	\N	\N	\N	210	2022-08-17 00:43:22.737087	2022-08-17 00:43:22.737087
7a0ab8a2-53a2-4710-8dc9-0a6fd362cf7e	Valid Palindrome with at most 1 deletion	https://leetcode.com/problems/valid-palindrome-ii/	\N	\N	\N	\N	211	2022-08-17 00:43:22.744952	2022-08-17 00:43:22.744952
f7d5eb2e-edff-439a-b0ff-11e1b79da887	Number of Provinces	https://leetcode.com/problems/number-of-provinces/	\N	\N	\N	\N	212	2022-08-17 00:43:22.753478	2022-08-17 00:43:22.753478
017e236f-b9b1-4614-83c6-899e941000ba	Sentence Similarity	https://leetcode.com/problems/sentence-similarity/	\N	\N	\N	\N	213	2022-08-17 00:43:22.76133	2022-08-17 00:43:22.76133
0ab12f92-0969-4fb7-a302-29240662ed6d	Island Perimeter	https://leetcode.com/problems/island-perimeter/	\N	\N	\N	\N	214	2022-08-17 00:43:22.776235	2022-08-17 00:43:22.776235
cd7d1b5e-0bb4-4b41-98e4-6a48c2974c8c	Deep copy a graph	https://leetcode.com/problems/clone-graph/	\N	\N	\N	\N	215	2022-08-17 00:43:22.78496	2022-08-17 00:43:22.78496
c7273cab-d718-49ae-a191-6d68bca473e9	Merge all overlapping intervals	https://leetcode.com/problems/merge-intervals/	\N	\N	\N	\N	216	2022-08-17 00:43:22.79294	2022-08-17 00:43:22.79294
1de03eec-621f-4fb2-967b-61167a852d3f	Closest Binary Search Tree Value	https://leetcode.com/problems/closest-binary-search-tree-value/	\N	\N	\N	\N	217	2022-08-17 00:43:22.801126	2022-08-17 00:43:22.801126
4fe6d127-aa0d-42dd-90d6-8e2ef56c7020	Divisor Game	https://leetcode.com/problems/divisor-game/	\N	\N	\N	\N	218	2022-08-17 00:43:22.809039	2022-08-17 00:43:22.809039
95b22b15-9c11-4202-8073-878010921cff	Delete Node in a Linked List	https://leetcode.com/problems/delete-node-in-a-linked-list/	\N	\N	\N	\N	219	2022-08-17 00:43:22.817218	2022-08-17 00:43:22.817218
92553bb3-4c87-4342-886a-4aa00f505191	Find the longest consecutive element sequence	https://leetcode.com/problems/longest-consecutive-sequence/solution/	\N	\N	\N	\N	220	2022-08-17 00:43:22.825112	2022-08-17 00:43:22.825112
f17a095e-4d62-4e72-a33d-9ef4a63c0f97	Count Substrings That Differ by One Character	https://leetcode.com/problems/count-substrings-that-differ-by-one-character/	\N	\N	\N	\N	221	2022-08-17 00:43:22.833471	2022-08-17 00:43:22.833471
641375bc-b3c8-47cd-8606-aae3291efc98	Group Shifted Strings	https://leetcode.com/problems/group-shifted-strings/	\N	\N	\N	\N	222	2022-08-17 00:43:22.841495	2022-08-17 00:43:22.841495
addb7f26-029b-46c2-9c91-43bfec667ad0	Increasing Order Search Tree	https://leetcode.com/problems/increasing-order-search-tree/	\N	\N	\N	\N	223	2022-08-17 00:43:22.84962	2022-08-17 00:43:22.84962
f3729093-cd98-44f9-8446-e76f3b0cd86a	Word Break-II	https://leetcode.com/problems/word-break-ii/	\N	\N	\N	\N	224	2022-08-17 00:43:22.857971	2022-08-17 00:43:22.857971
394cfe92-1f78-4afc-a487-1f7bf941ceba	Find the customer with the maximum wealth	https://leetcode.com/problems/richest-customer-wealth/	\N	\N	\N	\N	225	2022-08-17 00:43:22.866005	2022-08-17 00:43:22.866005
243eda9c-58c9-4823-b91d-06dbe87c399c	Interleaving String	https://leetcode.com/problems/interleaving-string/	\N	\N	\N	\N	226	2022-08-17 00:43:22.875027	2022-08-17 00:43:22.875027
892db439-cd0d-43b0-a53f-e4aade6997dd	Maximum Score	https://leetcode.com/problems/maximum-score-from-removing-stones/	\N	\N	\N	\N	227	2022-08-17 00:43:22.884578	2022-08-17 00:43:22.884578
eebc88d7-d262-451b-823c-2c69f4735934	3Sum - Find a triplet with a given sum	https://leetcode.com/problems/3sum/	\N	\N	\N	\N	228	2022-08-17 00:43:22.894418	2022-08-17 00:43:22.894418
57b97632-6fca-4166-8322-66a7725caf53	Valid Perfect Square	https://leetcode.com/problems/valid-perfect-square/	\N	\N	\N	\N	229	2022-08-17 00:43:22.905503	2022-08-17 00:43:22.905503
7ed530b0-5b26-4a5c-95cf-6b27cc19c9e2	Find the majority element in an array	https://leetcode.com/problems/majority-element/	\N	\N	\N	\N	230	2022-08-17 00:43:22.913121	2022-08-17 00:43:22.913121
abdf4d26-0e77-4300-9377-8404eb80bb1b	Dot Product of Two Sparse Vectors	https://leetcode.com/problems/dot-product-of-two-sparse-vectors/	\N	\N	\N	\N	231	2022-08-17 00:43:22.923322	2022-08-17 00:43:22.923322
09704757-4746-45a1-b65c-e7901d8ef75b	Maximum Average Pass Ratio	https://leetcode.com/problems/maximum-average-pass-ratio/	\N	\N	\N	\N	232	2022-08-17 00:43:22.932791	2022-08-17 00:43:22.932791
0cf99759-5b76-41df-a2be-ff19dfe2ba1c	Moving Average	https://leetcode.com/problems/moving-average-from-data-stream/	\N	\N	\N	\N	233	2022-08-17 00:43:22.943525	2022-08-17 00:43:22.943525
875b529f-be1b-47c6-b5e1-f1c506edb9c7	K Closest Points to Origin	https://leetcode.com/problems/k-closest-points-to-origin/	\N	\N	\N	\N	234	2022-08-17 00:43:22.951498	2022-08-17 00:43:22.951498
5b7a4f3d-6b43-4677-9cd4-7f703f44d504	Letter Tile Possibilities	https://leetcode.com/problems/letter-tile-possibilities/	\N	\N	\N	\N	235	2022-08-17 00:43:22.96355	2022-08-17 00:43:22.96355
21c65e3b-a5ec-4702-a384-81c245a3965f	Validate if a tree is a subtree of another tree	https://leetcode.com/problems/subtree-of-another-tree/	\N	\N	\N	\N	236	2022-08-17 00:43:22.971606	2022-08-17 00:43:22.971606
14189f01-24de-4b94-bd81-21b130064c37	Implement Quick Sort	https://leetcode.com/problems/sort-an-array/	\N	\N	\N	\N	237	2022-08-17 00:43:22.979807	2022-08-17 00:43:22.979807
f1ff118d-43d8-4f73-b1cf-a08772fbead1	Generate Subsets	https://leetcode.com/problems/subsets/	\N	\N	\N	\N	238	2022-08-17 00:43:22.989336	2022-08-17 00:43:22.989336
132e9e1f-3a27-413a-bb43-0646744398f0	Check if a string has valid parantheses	https://leetcode.com/problems/valid-parentheses	\N	\N	\N	\N	239	2022-08-17 00:43:22.997241	2022-08-17 00:43:22.997241
44be289f-a3b7-4d92-b6d7-e916e4758433	Diameter of Binary Tree	https://leetcode.com/problems/diameter-of-binary-tree/	\N	\N	\N	\N	240	2022-08-17 00:43:23.004994	2022-08-17 00:43:23.004994
64215621-b18f-441d-b709-143451d2bee3	Random Pick Index	https://leetcode.com/problems/random-pick-index/	\N	\N	\N	\N	241	2022-08-17 00:43:23.012585	2022-08-17 00:43:23.012585
cced6323-3aa3-47c9-a7d6-72eefdce0a57	Find the maximum consecutive ones in an array	https://leetcode.com/problems/max-consecutive-ones/	\N	\N	\N	\N	242	2022-08-17 00:43:23.0205	2022-08-17 00:43:23.0205
63450783-b653-4959-a53e-637a2271bf58	Continuous Subarray Sum	https://leetcode.com/problems/continuous-subarray-sum/	\N	\N	\N	\N	243	2022-08-17 00:43:23.028746	2022-08-17 00:43:23.028746
a2904c60-675d-48bc-90df-c75374262fa9	Design a time-based key-value data structure	https://leetcode.com/problems/time-based-key-value-store/	\N	\N	\N	\N	244	2022-08-17 00:43:23.037469	2022-08-17 00:43:23.037469
24c84585-0349-427e-a9ac-10549b5d3de6	Toeplitz Matrix	https://leetcode.com/problems/toeplitz-matrix/	\N	\N	\N	\N	245	2022-08-17 00:43:23.044859	2022-08-17 00:43:23.044859
8a237d89-6f5d-435f-b18c-e79629afc909	Counting Bits	https://leetcode.com/problems/counting-bits/	\N	\N	\N	\N	246	2022-08-17 00:43:23.052464	2022-08-17 00:43:23.052464
a79df524-cf89-4024-bc81-24fc10d5f1d9	Find word in a matrix	https://leetcode.com/problems/word-search/	\N	\N	\N	\N	247	2022-08-17 00:43:23.060041	2022-08-17 00:43:23.060041
487f7e6b-73aa-45bf-8087-01eca7449034	Longest Line Of Ones	https://leetcode.com/problems/max-area-of-island/	\N	\N	\N	\N	248	2022-08-17 00:43:23.069197	2022-08-17 00:43:23.069197
f6a66561-d43a-4fa5-925a-2821e746b3e6	Find the intersection of three arrays	https://leetcode.com/problems/intersection-of-three-sorted-arrays/	\N	\N	\N	\N	249	2022-08-17 00:43:23.076943	2022-08-17 00:43:23.076943
5f0833de-1cab-403a-b24c-457fc478fa0f	First Bad Version	https://leetcode.com/problems/first-bad-version/	\N	\N	\N	\N	250	2022-08-17 00:43:23.091984	2022-08-17 00:43:23.091984
0b9890a1-38d9-46b2-a065-31f9a6af3970	Find the running sum from an array	https://leetcode.com/problems/running-sum-of-1d-array/	\N	\N	\N	\N	251	2022-08-17 00:43:23.102078	2022-08-17 00:43:23.102078
e629c336-057d-4f49-b92d-67a8d011b7e7	Design a HitCounter	https://leetcode.com/problems/design-hit-counter/	\N	\N	\N	\N	252	2022-08-17 00:43:23.11016	2022-08-17 00:43:23.11016
d3b69b96-7930-4880-8de9-868a13675837	Insert Delete GetRandom O(1)	https://leetcode.com/problems/insert-delete-getrandom-o1/	\N	\N	\N	\N	253	2022-08-17 00:43:24.705953	2022-08-17 00:43:24.705953
\.


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_profile (user_id, name, email_id, org, referrer_id, created_at, updated_at) FROM stdin;
0c5f5a7e-e5d7-445a-aa8b-1377538301dc	Ravi Tandon	ravifreek63@gmail.com	DecoverHQ, Inc.	0c5f5a7e-e5d7-445a-aa8b-1377538301dc	2022-08-16 17:14:08.784231	2022-08-16 17:14:08.784237
17d67b18-85b5-4f3d-af64-06b5a3c8f7c7	Abhishek raj	abhishekraj2772@gmail.com	Airtel	17d67b18-85b5-4f3d-af64-06b5a3c8f7c7	2022-08-17 05:29:04.442395	2022-08-17 05:29:04.442399
511ca09c-87d3-4f1c-a57d-2ace6d28f3f0	\N	vinaykulhari828@gmail.com	\N	511ca09c-87d3-4f1c-a57d-2ace6d28f3f0	2022-08-17 07:36:24.95748	2022-08-17 07:36:24.95748
d50f62e5-9ab1-40c0-ba2d-d8bfa6196dab	Anshul Arya	anshularya11@gmail.com	NSIT	d50f62e5-9ab1-40c0-ba2d-d8bfa6196dab	2022-08-17 08:40:28.08643	2022-08-17 08:40:28.086431
2fb8b849-0357-49b6-b6a7-81aa6249d021	\N	sangsing126@gmail.com	\N	2fb8b849-0357-49b6-b6a7-81aa6249d021	2022-08-17 17:24:23.685804	2022-08-17 17:24:23.685804
f5efcf7e-0f45-4775-b563-bed72946475d	\N	andi.m.sultan@gmail.com	\N	f5efcf7e-0f45-4775-b563-bed72946475d	2022-08-17 17:29:01.669676	2022-08-17 17:29:01.669676
9739a3c7-cf8f-4016-a0f4-37a0884dff7e	\N	pradhuman.kumawat.in@gmail.com	\N	9739a3c7-cf8f-4016-a0f4-37a0884dff7e	2022-08-17 17:30:04.345397	2022-08-17 17:30:04.345397
80b82d07-070b-4dc7-bd67-83ce797a182b	\N	praveenjha010@gmail.com	\N	80b82d07-070b-4dc7-bd67-83ce797a182b	2022-08-17 17:33:39.285498	2022-08-17 17:33:39.285498
698b60a4-8198-4912-aeff-450d09b94c24	\N	ktripathi12@gmail.com	\N	698b60a4-8198-4912-aeff-450d09b94c24	2022-08-17 17:34:37.500244	2022-08-17 17:34:37.500244
94ce9b65-c17d-4f83-b900-d56999bacb1a	\N	sreepra64@gmail.com	\N	94ce9b65-c17d-4f83-b900-d56999bacb1a	2022-08-17 17:35:32.740294	2022-08-17 17:35:32.740294
62dea53a-f021-4ef0-96a2-304c81a09854	\N	vivek03.singh@icloud.com	\N	62dea53a-f021-4ef0-96a2-304c81a09854	2022-08-17 17:36:35.096257	2022-08-17 17:36:35.096257
91085705-4310-4efc-8ca1-77cda3f2eb91	\N	hikarthikbye@gmail.com	\N	91085705-4310-4efc-8ca1-77cda3f2eb91	2022-08-17 17:39:44.236888	2022-08-17 17:39:44.236888
0c916304-9479-4937-af63-26154335679c	\N	mpayushmp@gmail.com	\N	0c916304-9479-4937-af63-26154335679c	2022-08-17 17:40:55.164684	2022-08-17 17:40:55.164684
510a54fc-d323-409c-8d1f-31dd25832763	\N	sumirbindall@gmail.com	\N	510a54fc-d323-409c-8d1f-31dd25832763	2022-08-17 17:42:15.406013	2022-08-17 17:42:15.406013
0b6ce657-4614-410b-991f-6b48925d5967	\N	mehul.thakral@rediffmail.com	\N	0b6ce657-4614-410b-991f-6b48925d5967	2022-08-17 17:43:06.757737	2022-08-17 17:43:06.757737
6ca50a17-4600-4ff6-92a9-fe6bb56444eb	\N	vivek03.singh@gmail.com	\N	6ca50a17-4600-4ff6-92a9-fe6bb56444eb	2022-08-17 17:43:30.617402	2022-08-17 17:43:30.617402
4910864a-764e-59b5-811f-231c33c17261	Ravi Tandon	ravifreek63@gmail.com	\N	4910864a-764e-59b5-811f-231c33c17261	2022-08-18 11:58:28.136683	2022-08-18 11:58:28.136687
54f80943-7ce4-52a6-b612-c20288a19ed7	Ankit Kumar	ankit.2official@gmail.com	\N	54f80943-7ce4-52a6-b612-c20288a19ed7	2022-08-18 11:59:22.034174	2022-08-18 11:59:22.034177
6f34cda4-4908-4e51-afca-d1fad2c596ef	Raja Nikshith Katta	rajanikshith7@gmail.com	\N	6f34cda4-4908-4e51-afca-d1fad2c596ef	2022-08-18 14:35:07.238585	2022-08-18 14:35:07.238587
851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Mudit Choraria	muditchoraria97@gmail.com	Atlassian	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	2022-08-18 14:51:31.480536	2022-08-18 14:51:31.480538
ed8af5ab-130d-5216-9f04-7a75c547eadb	Manas Singh	manassingh100000@gmail.com	\N	ed8af5ab-130d-5216-9f04-7a75c547eadb	2022-08-19 16:29:13.628224	2022-08-19 16:29:13.628226
41889a94-1c53-504f-a03c-2a820e0a0bbe	Priya Arora	priya.arora.pinnacle@gmail.com	Oracle	41889a94-1c53-504f-a03c-2a820e0a0bbe	2022-08-18 19:35:40.239404	2022-08-18 19:35:40.239405
95eaf3c4-e546-5c12-9277-0d2bc7222146	Pradeep Yelamanti	pradeep.yelamanti@gmail.com	\N	95eaf3c4-e546-5c12-9277-0d2bc7222146	2022-08-18 23:08:24.374561	2022-08-18 23:08:24.374563
473b5965-b7c2-57c3-b093-6850fddb5b1c	Rehan Ali	azaanuddin4@gmail.com	\N	473b5965-b7c2-57c3-b093-6850fddb5b1c	2022-08-19 02:57:12.369407	2022-08-19 02:57:12.369409
aedf38d8-ee9a-55c3-8cbb-61cbe0b8483e	Raj Kumar	replytoraj81@gmail.com	\N	aedf38d8-ee9a-55c3-8cbb-61cbe0b8483e	2022-08-19 03:19:09.389689	2022-08-19 03:19:09.389691
0ec94480-6976-5ae8-8ff6-6f03080814b9	KRISHAN KUMAR	krishankumarkk08052002@gmail.com	\N	0ec94480-6976-5ae8-8ff6-6f03080814b9	2022-08-19 04:12:13.561886	2022-08-19 04:12:13.561888
eb8015f9-796e-44f6-b6ce-90ad0b7473e3	\N	yashagarwaljpr@gmail.com	\N	eb8015f9-796e-44f6-b6ce-90ad0b7473e3	2022-08-19 05:38:20.810934	2022-08-19 05:38:20.810934
adae3af0-c5fd-46d5-8aaa-3c408608882b	\N	irfan.arshad4@gmail.com	\N	adae3af0-c5fd-46d5-8aaa-3c408608882b	2022-08-19 05:39:00.943994	2022-08-19 05:39:00.943994
569b600d-82c0-59a7-b73f-e3b7f46a4110	sumit yadav	jamessumit67@gmail.com	\N	569b600d-82c0-59a7-b73f-e3b7f46a4110	2022-08-19 05:55:54.526442	2022-08-19 05:55:54.526447
bc76b2f3-34c1-5ec3-88bb-de14e742153d	Chinmay Manas	chinmay007manas@gmail.com	\N	bc76b2f3-34c1-5ec3-88bb-de14e742153d	2022-08-19 06:07:30.383335	2022-08-19 06:07:30.383339
704cc4ce-d55b-5407-beee-b8d2e742611b	MAUSAM KUMAR SUMAN	techipguy@gmail.com	\N	704cc4ce-d55b-5407-beee-b8d2e742611b	2022-08-19 06:44:32.229724	2022-08-19 06:44:32.229726
0a1a3ac6-c0f5-43db-a996-30c871b0ace3	Parth Mathur	parth.mthur@gmail.com	\N	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	2022-08-19 07:33:35.725909	2022-08-19 07:33:35.725911
3e066f29-40f3-5536-baa9-1f90ceba46a8	suryansh ranaut	suryanshandranaut@gmail.com	\N	3e066f29-40f3-5536-baa9-1f90ceba46a8	2022-08-19 10:42:09.547727	2022-08-19 10:42:09.547729
a17c6547-d1b4-54bf-83a5-9542a1971351	Ditikrushna Giri	diticuo062@gmail.com	\N	a17c6547-d1b4-54bf-83a5-9542a1971351	2022-08-19 10:53:09.171129	2022-08-19 10:53:09.171131
9271dfd7-2124-42f9-bf92-9cab59816082		yash.vijayvergiya@gmail.com	\N	9271dfd7-2124-42f9-bf92-9cab59816082	2022-08-19 14:47:29.942164	2022-08-19 14:47:29.942166
d78021be-e222-5910-96cd-8e68d4380909	chhagan mathuriya	clmathuriya@gmail.com	Thoughtspot	d78021be-e222-5910-96cd-8e68d4380909	2022-08-19 15:47:41.92329	2022-08-19 15:47:41.923292
be30b7c0-dd19-572b-b8eb-0b116523fe3b	Satyam Shivam Sundram	satyspy007@gmail.com	ThoughtSpot	be30b7c0-dd19-572b-b8eb-0b116523fe3b	2022-08-19 21:57:57.61867	2022-08-19 21:57:57.618699
3be54e13-77b8-55ff-b45a-1fb9818df51a	Ravi Tandon	ravitandon2@gmail.com	Decover	3be54e13-77b8-55ff-b45a-1fb9818df51a	2022-08-19 22:56:38.292851	2022-08-19 22:56:38.292859
54baac39-03f0-44e2-8c22-e88927b0afb3	<NamrataSanger/>	namratasanger55@gmail.com	University of Windsor	54baac39-03f0-44e2-8c22-e88927b0afb3	2022-08-19 23:03:42.581802	2022-08-19 23:03:42.58182
3fe8b089-5800-4e17-ac67-9e2057c0b91d	Vindeep Chaudhari	vindeepjr@gmail.com	\N	3fe8b089-5800-4e17-ac67-9e2057c0b91d	2022-08-20 09:26:09.315552	2022-08-20 09:26:09.315565
c8a847e1-2d2d-5d63-94bd-10edd506c1b4	Bhaskar Jha	jhabhaskar28@gmail.com	\N	c8a847e1-2d2d-5d63-94bd-10edd506c1b4	2022-08-20 10:09:06.975884	2022-08-20 10:09:06.975886
10e1acc3-1659-5333-8ac1-fc55ede2b477	SHIKHAR MANN	shikhar.mann0@gmail.com	\N	10e1acc3-1659-5333-8ac1-fc55ede2b477	2022-08-20 12:02:19.821685	2022-08-20 12:02:19.821687
95539ad8-5ec3-5bf3-bfdd-665b84b2b624	Gandhar Sathe	gandharsathe7@gmail.com	\N	95539ad8-5ec3-5bf3-bfdd-665b84b2b624	2022-08-20 16:07:15.382931	2022-08-20 16:07:15.382933
915f5b0b-19d2-588f-88a7-9de9fb9d5313	Zept	kakarrot.goku2003@gmail.com	\N	915f5b0b-19d2-588f-88a7-9de9fb9d5313	2022-08-20 17:45:23.755863	2022-08-20 17:45:23.755865
4544fe81-06f3-4660-bf96-3231fff90e7a		phijojoseph1@gmail.com	\N	4544fe81-06f3-4660-bf96-3231fff90e7a	2022-08-21 01:35:14.472069	2022-08-21 01:35:14.472071
6f8d2515-46a5-5160-87ef-2cdc72d732c8	Neeraj Sujan	nrj127@gmail.com	\N	6f8d2515-46a5-5160-87ef-2cdc72d732c8	2022-08-21 01:39:28.387698	2022-08-21 01:39:28.3877
1e4fc02f-91a0-549c-84e4-f8ec3b81c281	Rakesh kumar	soumyasunena@gmail.com	\N	1e4fc02f-91a0-549c-84e4-f8ec3b81c281	2022-08-21 01:59:29.085348	2022-08-21 01:59:29.08535
61ef23da-fed6-5142-b67a-2f4d2cf00b68	Amit Ranjan	amitranjan6714@gmail.com	\N	61ef23da-fed6-5142-b67a-2f4d2cf00b68	2022-08-21 02:08:42.973851	2022-08-21 02:08:42.973853
b0b1978f-8c5c-565b-8a31-eb0a2df822d0	Shahanad T.k	shahanad987@gmail.com	\N	b0b1978f-8c5c-565b-8a31-eb0a2df822d0	2022-08-21 02:23:50.606494	2022-08-21 02:23:50.606496
5d61c071-099a-5c7d-8612-82f89c5c0ac2	Upendra Kumar	upendra25312@gmail.com	\N	5d61c071-099a-5c7d-8612-82f89c5c0ac2	2022-08-21 02:30:57.644411	2022-08-21 02:30:57.644414
13f72a12-f574-5cba-ae92-cf62e0205133	Prateek Bose	prateekbose20011@gmail.com	\N	13f72a12-f574-5cba-ae92-cf62e0205133	2022-08-21 02:32:20.735121	2022-08-21 02:32:20.735123
5a42f217-99e5-5f73-a1a7-8aac89715fa1	Shameer Shaik	shaikshameer2762@gmail.com	\N	5a42f217-99e5-5f73-a1a7-8aac89715fa1	2022-08-21 02:51:34.342262	2022-08-21 02:51:34.342264
927a0d56-89f1-4b3a-bb3a-1f1fddf0d6b8	Abhilash Reddy	techary0@gmail.com	\N	927a0d56-89f1-4b3a-bb3a-1f1fddf0d6b8	2022-08-21 03:00:17.200899	2022-08-21 03:00:17.200901
07852e20-318f-5f47-a383-92459df8cc4d	Ryan G	mintla99ar@gmail.com	\N	07852e20-318f-5f47-a383-92459df8cc4d	2022-08-21 03:01:03.091722	2022-08-21 03:01:03.091724
06989bc3-d69a-5874-aa19-1c4a0195c3ac	venkatesh SQL	venkatesh.sql@gmail.com	\N	06989bc3-d69a-5874-aa19-1c4a0195c3ac	2022-08-21 03:06:17.609442	2022-08-21 03:06:17.609444
3c8bfead-3d5a-5638-a658-8a5d658e2c86	Rajnish Pandey	rajnish14ch41@gmail.com	\N	3c8bfead-3d5a-5638-a658-8a5d658e2c86	2022-08-21 03:07:46.03893	2022-08-21 03:07:46.038932
a8e54d7e-b5e7-4d02-87c9-5b8fb9df3f53		agdba.9@gmail.com	\N	a8e54d7e-b5e7-4d02-87c9-5b8fb9df3f53	2022-08-21 03:17:09.572524	2022-08-21 03:17:09.572525
fe909a7e-00e2-5b22-bcbc-7972e85dc921	abdul khader Shaik	abdulkhader.eee@gmail.com	\N	fe909a7e-00e2-5b22-bcbc-7972e85dc921	2022-08-21 03:26:07.982682	2022-08-21 03:26:07.982683
bd76f9a8-b1bb-566c-968a-613c0f6d35ce	Sagar Mohanty	sagar.mohanty88@googlemail.com	\N	bd76f9a8-b1bb-566c-968a-613c0f6d35ce	2022-08-21 03:26:44.368543	2022-08-21 03:26:44.368544
f9c11143-8758-5ebd-98d7-79241b23035e	sumanta ghosh	sumanengg.sg@gmail.com	\N	f9c11143-8758-5ebd-98d7-79241b23035e	2022-08-21 03:40:33.781195	2022-08-21 03:40:33.781196
6b1a3841-b5f5-5b5e-ad89-782861ecff4e	Ameya Phadke	ameyaphadke0@gmail.com	\N	6b1a3841-b5f5-5b5e-ad89-782861ecff4e	2022-08-21 03:42:11.900725	2022-08-21 03:42:11.900727
44992d7e-f942-525e-bf3c-ebb6c40ed3eb	Damodaram jeevan kumar	jeevandamodaram@gmail.com	\N	44992d7e-f942-525e-bf3c-ebb6c40ed3eb	2022-08-21 03:42:45.562392	2022-08-21 03:42:45.562393
e086eff6-4a06-532e-b134-7cca34b4c225	Yashwanth Kumar M V	mvyashwanthkumar@gmail.com	\N	e086eff6-4a06-532e-b134-7cca34b4c225	2022-08-21 03:51:14.713435	2022-08-21 03:51:14.713436
84a509b0-3f51-4b3f-8537-702603178428		sainathreddy864@gmail.com	\N	84a509b0-3f51-4b3f-8537-702603178428	2022-08-21 03:53:31.308775	2022-08-21 03:53:31.308776
433f9bff-86c6-4598-9975-2bbe9353d142		t.shiva548@gmail.com	\N	433f9bff-86c6-4598-9975-2bbe9353d142	2022-08-21 04:35:21.520924	2022-08-21 04:35:21.520925
f26058b6-dba5-53c4-ac67-ecf25b164940	Sahil	delhilife143@gmail.com	Exl	f26058b6-dba5-53c4-ac67-ecf25b164940	2022-08-21 04:41:39.652446	2022-08-21 04:41:39.652447
a39faeee-afd5-41e7-844d-efcac5b63f4d		tanmeshnm@gmail.com	\N	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	2022-08-21 04:44:53.411421	2022-08-21 04:44:53.411422
f1374260-9217-456d-a383-9f639fa93ffd		subudhi.subudhi@gmail.com	\N	f1374260-9217-456d-a383-9f639fa93ffd	2022-08-21 04:47:22.736634	2022-08-21 04:47:22.736635
75fa12ad-9637-5049-8bc9-5cc888b6923a	Rishi Bharadwaj	rishibharadwaj020@gmail.com	\N	75fa12ad-9637-5049-8bc9-5cc888b6923a	2022-08-21 04:54:38.73071	2022-08-21 04:54:38.730711
14419a61-58ff-5640-847f-61988bd18153	Shiva Varun Ramasagaram	ramasagaramshivavarun@gmail.com	\N	14419a61-58ff-5640-847f-61988bd18153	2022-08-21 05:25:06.513783	2022-08-21 05:25:06.513785
a3b847c0-67cd-483a-aecc-201d223895b7		rathisakshi2709@gmail.com	\N	a3b847c0-67cd-483a-aecc-201d223895b7	2022-08-21 05:42:01.463929	2022-08-21 05:42:01.463931
272368b0-dfe3-40f7-9f11-02902471339a		ajitbabar99@gmail.com	\N	272368b0-dfe3-40f7-9f11-02902471339a	2022-08-21 05:44:00.613854	2022-08-21 05:44:00.613856
47cf72c7-1572-56c0-9359-9302eda5c7a3	Venkata Avinash Mudragadda	mvanaidu2077@gmail.com	\N	47cf72c7-1572-56c0-9359-9302eda5c7a3	2022-08-21 05:49:11.238124	2022-08-21 05:49:11.238125
dc7eeed3-88cf-5893-91f2-9ead58d29c22	Daksh Lakhotiya	lakhotiyadk02@gmail.com	\N	a3b847c0-67cd-483a-aecc-201d223895b7	2022-08-21 05:54:20.139152	2022-08-21 05:54:20.139153
2273c544-65ac-59c2-9886-05f344e98cd0	Rohit Saini 17227	rohits17@iiserb.ac.in	\N	2273c544-65ac-59c2-9886-05f344e98cd0	2022-08-21 05:54:26.197035	2022-08-21 05:54:26.197036
93fb501d-8a7b-5234-b372-78a55b0092dc	Vichitr Gandas	vichitrgandas@gmail.com	CodeDrills	93fb501d-8a7b-5234-b372-78a55b0092dc	2022-08-21 06:34:30.819206	2022-08-21 06:34:30.819207
f5be6542-0ac9-5ad5-9188-030d7128a3fd	Harish Gautam	gautam33776@gmail.com	\N	f5be6542-0ac9-5ad5-9188-030d7128a3fd	2022-08-21 07:09:36.805735	2022-08-21 07:09:36.805736
cd718717-f3f3-5ec2-861f-d92bc04677c4	Ankit Atreja	ankit.atreja03@gmail.com	\N	cd718717-f3f3-5ec2-861f-d92bc04677c4	2022-08-21 07:12:50.216136	2022-08-21 07:12:50.216138
06612326-c546-5d1f-814f-54ac17836de2	Siddhant Singh	siddhant000004@gmail.com	\N	06612326-c546-5d1f-814f-54ac17836de2	2022-08-21 07:15:16.059369	2022-08-21 07:15:16.05937
50660fc7-8f67-5449-83c5-30e1421d162b	Dhanya Hegde	dhanyavhegde01@gmail.com	\N	50660fc7-8f67-5449-83c5-30e1421d162b	2022-08-21 07:45:32.090245	2022-08-21 07:45:32.090246
c31c53bf-40bb-449f-8d7c-49fed4d5c93e		arunlodhi0256@gmail.com	\N	c31c53bf-40bb-449f-8d7c-49fed4d5c93e	2022-08-21 08:32:42.4677	2022-08-21 08:32:42.467701
48a1787d-096b-5cc9-a6da-a7d8b19c2dc3	Arun Lodhi	arunlodhi0256@gmail.com	\N	48a1787d-096b-5cc9-a6da-a7d8b19c2dc3	2022-08-21 08:32:43.336833	2022-08-21 08:32:43.336835
17839c5e-df81-5b80-a5f4-6ba71bf2abb2	Mahaboob sk	skmahaboob145@gmail.com	\N	17839c5e-df81-5b80-a5f4-6ba71bf2abb2	2022-08-21 08:36:14.071492	2022-08-21 08:36:14.071493
8d7c4066-7138-577d-b4f4-4ed13c44d3d5	Abhishek B	abhishekarjs@gmail.com	\N	8d7c4066-7138-577d-b4f4-4ed13c44d3d5	2022-08-21 10:08:59.453018	2022-08-21 10:08:59.453019
b802e8ec-a717-5085-8af4-50d3cb69b922	abbas Khan	abbasniazi2@gmail.com	\N	b802e8ec-a717-5085-8af4-50d3cb69b922	2022-08-21 10:34:49.560542	2022-08-21 10:34:49.560543
9e513a8b-d5a5-4aee-9c13-945326961ed8		preetikommana099@gmail.com	\N	9e513a8b-d5a5-4aee-9c13-945326961ed8	2022-08-21 11:01:50.18309	2022-08-21 11:01:50.183092
690f4262-0c17-5256-aa52-b8c42bf0c170	mayank maheshwari	mayankmaheshwari344@gmail.com	\N	690f4262-0c17-5256-aa52-b8c42bf0c170	2022-08-21 12:12:10.676297	2022-08-21 12:12:10.676299
95a5f7e8-7ddd-5ef5-b380-6685ad6c176c	Aritra Sasmal	aritrasasmal@gmail.com	\N	95a5f7e8-7ddd-5ef5-b380-6685ad6c176c	2022-08-21 12:38:13.585485	2022-08-21 12:38:13.585486
70c7f6b6-718c-408b-b18a-f5fd4bc7b365		susmitha188@yahoo.com	\N	70c7f6b6-718c-408b-b18a-f5fd4bc7b365	2022-08-21 13:00:58.736275	2022-08-21 13:00:58.736276
2f100aed-cdb7-4525-9b10-7f4924560a88		raju.az@hotmail.com	\N	2f100aed-cdb7-4525-9b10-7f4924560a88	2022-08-21 13:46:00.010001	2022-08-21 13:46:00.010002
98e42333-af0c-4934-a633-2cbd315c4839	Nikhil	nikhilmittal3434@gmail.com	\N	98e42333-af0c-4934-a633-2cbd315c4839	2022-08-21 14:38:05.353271	2022-08-21 14:38:05.353272
361bb2a4-8145-5b2b-ac16-bc97effa4fab	Govind Soni	sonigovind07@gmail.com	\N	361bb2a4-8145-5b2b-ac16-bc97effa4fab	2022-08-21 14:40:19.837921	2022-08-21 14:40:19.837922
c8d154f4-30bf-5a5a-a574-db2dd4fbc2ec	Deepak Mishra	ddd.mishra007@gmail.com	\N	c8d154f4-30bf-5a5a-a574-db2dd4fbc2ec	2022-08-21 15:16:45.527493	2022-08-21 15:16:45.527495
c3cf5321-c46c-5bce-8548-84b4f25b2499	Neelam Bugaliya	neelambugaliyanitk@gmail.com	\N	c3cf5321-c46c-5bce-8548-84b4f25b2499	2022-08-21 16:26:27.135172	2022-08-21 16:26:27.135173
a80f7817-1ab5-5443-9756-4fa2b303a8a1	A	paraschhabra101@gmail.com	\N	a80f7817-1ab5-5443-9756-4fa2b303a8a1	2022-08-22 03:31:41.29025	2022-08-22 03:31:41.290251
c68a663a-5fd8-4fee-a0ed-58dbf39a4af0		perrosupapa@gmail.com	\N	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	2022-08-22 04:18:30.82159	2022-08-22 04:18:30.821591
adb11103-6f4c-5b2f-b277-556096eb25dd	Ashish Vyas	ashish100488@gmail.com	\N	adb11103-6f4c-5b2f-b277-556096eb25dd	2022-08-22 10:56:51.729828	2022-08-22 10:56:51.72983
10914a53-a060-53ee-905f-f9ae36bda6c5	Sachin garampalli	channayyagarampalli@gmail.com	\N	10914a53-a060-53ee-905f-f9ae36bda6c5	2022-08-22 13:19:16.632408	2022-08-22 13:19:16.632409
9aa2daad-578e-5888-a3a4-83f8993053a5	SwastikaOfficial Singh	swastika.official.singh@gmail.com	\N	9aa2daad-578e-5888-a3a4-83f8993053a5	2022-08-22 13:54:11.068916	2022-08-22 13:54:11.068918
5586ba06-da7e-5e75-b2fe-3b286d2f91f1	Rishant Jain	rishantsmailbox@gmail.com	\N	5586ba06-da7e-5e75-b2fe-3b286d2f91f1	2022-08-22 16:11:31.49674	2022-08-22 16:11:31.496742
a07b0b93-8f90-46e2-9edf-284c1bc2d32d		pyrosec1@gmail.com	\N	a07b0b93-8f90-46e2-9edf-284c1bc2d32d	2022-08-22 16:39:58.407126	2022-08-22 16:39:58.40713
\.


--
-- Data for Name: user_submission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_submission (submission_id, user_id, problem_name, problem_link, solution_link, created_at, updated_at) FROM stdin;
fa90c49a-899b-4d13-9aff-00dd433f36ba	0c5f5a7e-e5d7-445a-aa8b-1377538301dc	Add to Array-Form of Integer	https://leetcode.com/problems/add-to-array-form-of-integer/	https://github.com/ravitandon90/LeetCode-TopProblems/blob/main/Array/AddToArrayForm/cpp/AddToArrayForm.cpp	2022-08-16 16:53:11.338052	2022-08-16 16:53:11.338054
64d03cee-ba69-4624-a4b6-00b97aacb90a	0c5f5a7e-e5d7-445a-aa8b-1377538301dc	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	https://github.com/ravitandon90/LeetCode-TopProblems/blob/main/BitManipulation/SubsetXORSum.cpp	2022-08-17 05:54:17.217341	2022-08-17 05:54:17.217342
93be84af-6430-4fc2-b969-6f884bde0727	d50f62e5-9ab1-40c0-ba2d-d8bfa6196dab	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	https://github.com/anshulkhatkar11/Code-Submissions/tree/main/anshulkhatkar11	2022-08-17 11:49:41.421424	2022-08-17 11:49:41.421425
2365baa6-aee8-412c-8d25-34ca5d6010cc	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	https://github.com/parth191220/300-Days-Leetcode-Solution/tree/main	2022-08-17 18:00:42.601627	2022-08-17 18:00:42.601628
d217e5d7-fa86-4545-b038-d91a7974b585	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	https://github.com/parth191220/300-Days-Leetcode-Solution/tree/main	2022-08-17 18:00:50.970568	2022-08-17 18:00:50.970569
90adabf5-10fc-4b78-b367-4161d8bdfb74	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Subset XOR Sum	https://leetcode.com/problems/sum-of-all-subset-xor-totals/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/1863_Sum_of_All_Subset_XOR_Totals.cpp	2022-08-17 18:20:29.080888	2022-08-17 18:20:29.08089
60b07703-70da-416b-a371-79795f066b98	54f80943-7ce4-52a6-b612-c20288a19ed7	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/blob/main/Ravi-Tandon/AddToArrayForm.cpp	2022-08-18 12:35:12.446549	2022-08-18 12:35:12.446551
66237dd1-a232-4f9a-9827-493ee828060f	54f80943-7ce4-52a6-b612-c20288a19ed7	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/blob/main/Ravi-Tandon/AddToArrayForm.cpp	2022-08-18 12:35:47.062861	2022-08-18 12:35:47.062863
2682ce27-909a-4e8c-b525-8336397a0fb5	6f34cda4-4908-4e51-afca-d1fad2c596ef	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/blob/main/raj986/Sum%20of%20All%20Subset%20XOR%20Totals	2022-08-18 14:34:42.972965	2022-08-18 14:34:42.972966
eafe6f89-81e5-4d5e-b77e-7bf7456533ce	d50f62e5-9ab1-40c0-ba2d-d8bfa6196dab	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/blob/main/anshulkhatkar11/Day2	2022-08-18 14:38:22.598458	2022-08-18 14:38:22.59846
94efeb40-a19f-4722-8217-52e944e0f182	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/muditchoraria/leetcode/blob/7da3e0e771b94b596eebaefee00ba88139f9f2c6/242-valid-anagram/242-valid-anagram.cpp	2022-08-18 14:41:36.126086	2022-08-18 14:41:36.126088
53d6d62b-93d2-4f1c-b340-c313399c4cdc	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/242._Valid_Anagram.cpp	2022-08-18 18:49:31.763256	2022-08-18 18:49:31.763257
d1b34022-5b44-46bc-bd15-5741c5bcaaef	54baac39-03f0-44e2-8c22-e88927b0afb3	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/pull/11	2022-08-18 22:58:31.793522	2022-08-18 22:58:31.793523
dadb0642-c030-4499-ad59-9528296e2b59	54baac39-03f0-44e2-8c22-e88927b0afb3	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/tree/main/Namrata-Sanger	2022-08-18 22:59:05.711733	2022-08-18 22:59:05.711734
09f06ca7-4a92-4179-becb-9fcb2da9f09a	473b5965-b7c2-57c3-b093-6850fddb5b1c	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Check%20for%20anagrams	2022-08-19 02:52:06.732796	2022-08-19 02:52:06.732798
6c6cebe7-3ed5-4018-9748-7675f8f03d01	6f34cda4-4908-4e51-afca-d1fad2c596ef	Check if two strings are valid anagrams	https://leetcode.com/problems/valid-anagram/	https://github.com/ravitandon90/Code-Submissions/blob/main/raj986/Valid%20Anagram	2022-08-19 02:53:35.878627	2022-08-19 02:53:35.878628
2ca30f84-49c4-4291-b5ab-22fd2371c6c7	569b600d-82c0-59a7-b73f-e3b7f46a4110	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/sumityadav7/300-days-coding/blob/main/Sumit-Yadav/Implemet-trie	2022-08-19 06:32:08.137268	2022-08-19 06:32:08.13727
32205375-ad77-4cfb-8ed3-7672e8c4488a	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/muditchoraria/Code-Submissions/blob/muditchoraria-patch-2-implement-prefix-tree/Mudit%20Choraria/trie-prefix-tree.cpp	2022-08-19 17:11:42.498938	2022-08-19 17:11:42.49894
2a021014-35d6-4ef2-9be4-ad1544928870	d78021be-e222-5910-96cd-8e68d4380909	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/ravitandon90/Code-Submissions/pull/15	2022-08-19 17:11:43.566085	2022-08-19 17:11:43.566087
f30e4987-680d-4c31-8664-a591dbd52d78	0a1a3ac6-c0f5-43db-a996-30c871b0ace3	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/ravitandon90/Code-Submissions/pull/17	2022-08-19 17:46:25.166343	2022-08-19 17:46:25.166344
84b7fcbd-b3e8-44fc-ae36-e12dc4ad0e46	be30b7c0-dd19-572b-b8eb-0b116523fe3b	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/vamsaty/Code-Submissions/blob/main/satyam-sundaram/19-08-22/design-a-trie.go	2022-08-19 18:50:06.693874	2022-08-19 18:50:06.693876
ec644a3c-585c-42de-b9e9-99b3266218bb	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/208_Implement_Trie_Prefix_Tree.cpp	2022-08-19 20:06:02.198975	2022-08-19 20:06:02.198976
65f78841-3b8d-4f6b-884c-df89ce196075	473b5965-b7c2-57c3-b093-6850fddb5b1c	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Design%20a%20trie	2022-08-19 20:44:15.78838	2022-08-19 20:44:15.788382
c72644dc-00a6-48d0-9df6-aaaa38ecc0a2	54baac39-03f0-44e2-8c22-e88927b0afb3	Design a Trie	https://leetcode.com/problems/implement-trie-prefix-tree/	https://github.com/ravitandon90/Code-Submissions/pull/21	2022-08-19 22:47:51.3022	2022-08-19 22:47:51.302202
2d9de8c1-6fed-4565-b8f2-80839e4743a7	41889a94-1c53-504f-a03c-2a820e0a0bbe	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/ravitandon90/Code-Submissions/pull/23#pullrequestreview-1079475979	2022-08-20 06:36:11.501141	2022-08-20 06:36:11.501142
d834db97-f1e4-4873-b246-929b2f6e18fc	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/muditchoraria/Code-Submissions/blob/main/Mudit%20Choraria/max-boxes-on-truck.cpp	2022-08-20 06:44:02.343151	2022-08-20 06:44:02.343153
2ebdc972-f716-4513-b45d-ab1c311370e2	d78021be-e222-5910-96cd-8e68d4380909	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/ravitandon90/Code-Submissions/pull/25	2022-08-20 12:42:08.724276	2022-08-20 12:42:08.724278
d0ef4a58-4a2d-4dad-8634-68ff537904d5	be30b7c0-dd19-572b-b8eb-0b116523fe3b	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/vamsaty/Code-Submissions/blob/main/satyam-sundaram/max-units-on-truck.go	2022-08-20 16:09:58.377286	2022-08-20 16:09:58.377289
c6374b70-c536-4eb1-a90c-64d6b1d0aeae	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/1710_Maximum_Units_on_a_Truck.cpp	2022-08-20 16:24:46.369837	2022-08-20 16:24:46.369838
90859dda-74fa-4815-adf9-aeb4e665aee4	473b5965-b7c2-57c3-b093-6850fddb5b1c	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Maximum%20units%20on%20a%20truck	2022-08-20 19:59:25.139592	2022-08-20 19:59:25.139595
5298e2a6-70b8-4e02-99dc-11979cf00abd	473b5965-b7c2-57c3-b093-6850fddb5b1c	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Maximum%20units%20on%20a%20truck	2022-08-20 19:59:28.126557	2022-08-20 19:59:28.126559
65fa361b-1b8f-404f-9a90-910d7f76af9c	54baac39-03f0-44e2-8c22-e88927b0afb3	Maximum Units on a Truck	https://leetcode.com/problems/maximum-units-on-a-truck/	https://github.com/ravitandon90/Code-Submissions/pull/28	2022-08-21 00:19:33.969966	2022-08-21 00:19:33.969968
a120afc4-9cfe-43dc-bffd-c2501f6ac5f9	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/muditchoraria/Code-Submissions/blob/main/Mudit%20Choraria/kth-missing-number.cpp	2022-08-21 04:22:05.084979	2022-08-21 04:22:05.084981
9c0257ee-af67-487e-81ea-525f7432c33d	dc7eeed3-88cf-5893-91f2-9ead58d29c22	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/daksh890/Code-Submissions/blob/patch-1/Daksh-Lakhotiya/1539.%20Kth%20Missing%20Positive%20Number	2022-08-21 07:47:54.858622	2022-08-21 07:47:54.858624
31c5e27b-c376-495e-aa3d-368c1025dcfa	d78021be-e222-5910-96cd-8e68d4380909	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/ravitandon90/Code-Submissions/pull/33	2022-08-21 07:49:14.656418	2022-08-21 07:49:14.65642
c91041c7-b588-4059-ac51-68a38ea3e855	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/1539_Kth_Missing_Positive_Number.cpp	2022-08-21 10:35:20.809554	2022-08-21 10:35:20.809555
c431fa2d-50ab-4cb4-be86-910af4d0245a	6f8d2515-46a5-5160-87ef-2cdc72d732c8	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/sujanneeraj/data-structures-and-algorithms/tree/fec1ebcff91b2619093f2be878d6dbad5f2eb988/1539-kth-missing-positive-number	2022-08-21 11:54:36.713562	2022-08-21 11:54:36.713564
86800dae-93b7-4f49-b927-3897a20bc86b	473b5965-b7c2-57c3-b093-6850fddb5b1c	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Kth%20Missing%20Positive%20number	2022-08-21 18:46:27.449049	2022-08-21 18:46:27.449051
ddb1ac0c-81bd-43fe-b075-cb3866e9ce93	54baac39-03f0-44e2-8c22-e88927b0afb3	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/Namratasanger/Code-Submissions/pull/4	2022-08-21 18:59:08.526982	2022-08-21 18:59:08.526984
0b304815-b8f9-4052-9aec-8e0098431217	be30b7c0-dd19-572b-b8eb-0b116523fe3b	Find the Kth Missing Positive Element in an array	https://leetcode.com/problems/kth-missing-positive-number/	https://github.com/vamsaty/Code-Submissions/blob/main/satyam-sundaram/kth_missing_num.go	2022-08-21 19:03:42.008033	2022-08-21 19:03:42.008038
2fc649f8-8ed9-4c89-bb87-a26ba75913eb	d50f62e5-9ab1-40c0-ba2d-d8bfa6196dab	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/anshulkhatkar11/Code-Submissions/blob/main/anshulkhatkar11/Day6	2022-08-22 05:42:19.28229	2022-08-22 05:42:19.282294
29b16ea1-f300-43f5-a88e-0f932bf7d837	851a16f6-1d2c-5a2e-b2a4-ac6e43b75c20	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/muditchoraria/Code-Submissions/blob/main/Mudit%20Choraria/check-if-it-is-a-straight-line.cpp	2022-08-22 08:29:03.927934	2022-08-22 08:29:03.927935
1e04ade4-2b0f-4beb-b33a-9343865871bc	d78021be-e222-5910-96cd-8e68d4380909	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/ravitandon90/Code-Submissions/pull/38	2022-08-22 09:40:43.469598	2022-08-22 09:40:43.4696
45c6c17f-e9b2-4a09-9240-0691118f1d56	d78021be-e222-5910-96cd-8e68d4380909	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/ravitandon90/Code-Submissions/pull/38	2022-08-22 09:40:44.910486	2022-08-22 09:40:44.910487
c4f5e592-507a-42a7-9a64-0b06bf224c75	6f8d2515-46a5-5160-87ef-2cdc72d732c8	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/sujanneeraj/data-structures-and-algorithms/blob/344535e11ef949955601a2b4300a03d85f43e5f8/1232-check-if-it-is-a-straight-line/1232-check-if-it-is-a-straight-line.py	2022-08-22 10:16:47.6429	2022-08-22 10:16:47.642901
15999cf0-d30b-42da-bf86-a76a7daad293	10914a53-a060-53ee-905f-f9ae36bda6c5	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/Indicgamer/SOLN/blob/main/answer1	2022-08-22 14:02:56.83463	2022-08-22 14:02:56.834631
ee2053a5-3f51-4dbd-beee-a7a9df752f17	3fe8b089-5800-4e17-ac67-9e2057c0b91d	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/Vindeep07/Code-Submissions/blob/main/Vindeep-Chaudhari/1232_Check_If_It_Is_a_Straight_Line	2022-08-22 14:12:30.048394	2022-08-22 14:12:30.048395
4f57dc06-fcf5-4668-aab0-0058f7e022a4	be30b7c0-dd19-572b-b8eb-0b116523fe3b	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/vamsaty/Code-Submissions/blob/main/satyam-sundaram/check_straight_line.go	2022-08-22 16:04:52.998407	2022-08-22 16:04:52.998408
aee7decc-d83d-477e-89d2-b5f968266250	473b5965-b7c2-57c3-b093-6850fddb5b1c	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/The-Ali02/Code-Submissions/blob/main/Rehan%20Ali%20Farooqui/Rehan%20:%20Check%20if%20it%20is%20a%20straight%20line	2022-08-22 16:17:27.026484	2022-08-22 16:17:27.026485
40af5a79-74f9-4ef8-a270-7fd5c2a59a85	54baac39-03f0-44e2-8c22-e88927b0afb3	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/ravitandon90/Code-Submissions/pull/41	2022-08-22 16:26:40.606881	2022-08-22 16:26:40.606882
8a06a097-7f4b-4198-a3de-ec7e2337b54e	5586ba06-da7e-5e75-b2fe-3b286d2f91f1	Check if the coordinates are in a straight line	https://leetcode.com/problems/check-if-it-is-a-straight-line/	https://github.com/code-n-chill/Code-Submissions/blob/main/Rishant-Jain/1232.%20Check%20If%20It%20Is%20a%20Straight%20Line.cpp	2022-08-22 16:42:04.433137	2022-08-22 16:42:04.433139
\.


--
-- Name: problem_description problem_jpa_data_store_credential_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.problem_description
    ADD CONSTRAINT problem_jpa_data_store_credential_pkey PRIMARY KEY (problem_id);


--
-- Name: user_submission submission_jpa_data_store_credential_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_submission
    ADD CONSTRAINT submission_jpa_data_store_credential_pkey PRIMARY KEY (submission_id);


--
-- Name: user_profile user_jpa_data_store_credential_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT user_jpa_data_store_credential_pkey PRIMARY KEY (user_id);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

