/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.TokenQueue
 *  org.jsoup.select.CombiningEvaluator$And
 *  org.jsoup.select.CombiningEvaluator$Or
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.Evaluator$AllElements
 *  org.jsoup.select.Evaluator$Attribute
 *  org.jsoup.select.Evaluator$AttributeStarting
 *  org.jsoup.select.Evaluator$AttributeWithValue
 *  org.jsoup.select.Evaluator$AttributeWithValueContaining
 *  org.jsoup.select.Evaluator$AttributeWithValueEnding
 *  org.jsoup.select.Evaluator$AttributeWithValueMatching
 *  org.jsoup.select.Evaluator$AttributeWithValueNot
 *  org.jsoup.select.Evaluator$AttributeWithValueStarting
 *  org.jsoup.select.Evaluator$Class
 *  org.jsoup.select.Evaluator$ContainsData
 *  org.jsoup.select.Evaluator$ContainsOwnText
 *  org.jsoup.select.Evaluator$ContainsText
 *  org.jsoup.select.Evaluator$ContainsWholeOwnText
 *  org.jsoup.select.Evaluator$ContainsWholeText
 *  org.jsoup.select.Evaluator$Id
 *  org.jsoup.select.Evaluator$IndexEquals
 *  org.jsoup.select.Evaluator$IndexGreaterThan
 *  org.jsoup.select.Evaluator$IndexLessThan
 *  org.jsoup.select.Evaluator$IsEmpty
 *  org.jsoup.select.Evaluator$IsFirstChild
 *  org.jsoup.select.Evaluator$IsFirstOfType
 *  org.jsoup.select.Evaluator$IsLastChild
 *  org.jsoup.select.Evaluator$IsLastOfType
 *  org.jsoup.select.Evaluator$IsNthChild
 *  org.jsoup.select.Evaluator$IsNthLastChild
 *  org.jsoup.select.Evaluator$IsNthLastOfType
 *  org.jsoup.select.Evaluator$IsNthOfType
 *  org.jsoup.select.Evaluator$IsOnlyChild
 *  org.jsoup.select.Evaluator$IsOnlyOfType
 *  org.jsoup.select.Evaluator$IsRoot
 *  org.jsoup.select.Evaluator$MatchText
 *  org.jsoup.select.Evaluator$Matches
 *  org.jsoup.select.Evaluator$MatchesOwn
 *  org.jsoup.select.Evaluator$MatchesWholeOwnText
 *  org.jsoup.select.Evaluator$MatchesWholeText
 *  org.jsoup.select.Evaluator$Tag
 *  org.jsoup.select.Evaluator$TagEndsWith
 *  org.jsoup.select.Selector$SelectorParseException
 *  org.jsoup.select.StructuralEvaluator$Has
 *  org.jsoup.select.StructuralEvaluator$ImmediateParentRun
 *  org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling
 *  org.jsoup.select.StructuralEvaluator$Is
 *  org.jsoup.select.StructuralEvaluator$Not
 *  org.jsoup.select.StructuralEvaluator$Parent
 *  org.jsoup.select.StructuralEvaluator$PreviousSibling
 *  org.jsoup.select.StructuralEvaluator$Root
 */
package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

public class QueryParser {
    private final TokenQueue tq;
    private final String query;
    private static final String[] AttributeEvals;
    private static final Pattern NTH_B;
    private static final char[] Combinators;
    private final List<Evaluator> evals = new ArrayList<Evaluator>();
    private static final Pattern NTH_AB;

    private Evaluator consumeEvaluator() {
        if (this.tq.matchChomp("#")) {
            return this.byId();
        }
        if (this.tq.matchChomp(".")) {
            return this.byClass();
        }
        if (this.tq.matchesWord()) return this.byTag();
        if (this.tq.matches("*|")) {
            return this.byTag();
        }
        if (this.tq.matches("[")) {
            return this.byAttribute();
        }
        if (this.tq.matchChomp("*")) {
            return new Evaluator.AllElements();
        }
        if (!this.tq.matchChomp(":")) throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", new Object[]{this.query, this.tq.remainder()});
        return this.parsePseudoSelector();
    }

    private String consumeParens() {
        return this.tq.chompBalanced('(', ')');
    }

    private Evaluator containsData() {
        String searchText = TokenQueue.unescape((String)this.consumeParens());
        Validate.notEmpty((String)searchText, (String)":containsData(text) query must not be empty");
        return new Evaluator.ContainsData(searchText);
    }

    private Evaluator parsePseudoSelector() {
        String pseudo;
        switch (pseudo = this.tq.consumeCssIdentifier()) {
            case "lt": {
                return new Evaluator.IndexLessThan(this.consumeIndex());
            }
            case "gt": {
                return new Evaluator.IndexGreaterThan(this.consumeIndex());
            }
            case "eq": {
                return new Evaluator.IndexEquals(this.consumeIndex());
            }
            case "has": {
                return this.has();
            }
            case "is": {
                return this.is();
            }
            case "contains": {
                return this.contains(false);
            }
            case "containsOwn": {
                return this.contains(true);
            }
            case "containsWholeText": {
                return this.containsWholeText(false);
            }
            case "containsWholeOwnText": {
                return this.containsWholeText(true);
            }
            case "containsData": {
                return this.containsData();
            }
            case "matches": {
                return this.matches(false);
            }
            case "matchesOwn": {
                return this.matches(true);
            }
            case "matchesWholeText": {
                return this.matchesWholeText(false);
            }
            case "matchesWholeOwnText": {
                return this.matchesWholeText(true);
            }
            case "not": {
                return this.not();
            }
            case "nth-child": {
                return this.cssNthChild(false, false);
            }
            case "nth-last-child": {
                return this.cssNthChild(true, false);
            }
            case "nth-of-type": {
                return this.cssNthChild(false, true);
            }
            case "nth-last-of-type": {
                return this.cssNthChild(true, true);
            }
            case "first-child": {
                return new Evaluator.IsFirstChild();
            }
            case "last-child": {
                return new Evaluator.IsLastChild();
            }
            case "first-of-type": {
                return new Evaluator.IsFirstOfType();
            }
            case "last-of-type": {
                return new Evaluator.IsLastOfType();
            }
            case "only-child": {
                return new Evaluator.IsOnlyChild();
            }
            case "only-of-type": {
                return new Evaluator.IsOnlyOfType();
            }
            case "empty": {
                return new Evaluator.IsEmpty();
            }
            case "root": {
                return new Evaluator.IsRoot();
            }
            case "matchText": {
                return new Evaluator.MatchText();
            }
        }
        throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", new Object[]{this.query, this.tq.remainder()});
    }

    private Evaluator contains(boolean own) {
        String query = own ? ":containsOwn" : ":contains";
        String searchText = TokenQueue.unescape((String)this.consumeParens());
        Validate.notEmpty((String)searchText, (String)(query + "(text) query must not be empty"));
        return own ? new Evaluator.ContainsOwnText(searchText) : new Evaluator.ContainsText(searchText);
    }

    private Evaluator matches(boolean own) {
        String query = own ? ":matchesOwn" : ":matches";
        String regex = this.consumeParens();
        Validate.notEmpty((String)regex, (String)(query + "(regex) query must not be empty"));
        return own ? new Evaluator.MatchesOwn(Pattern.compile(regex)) : new Evaluator.Matches(Pattern.compile(regex));
    }

    private void combinator(char combinator) {
        Evaluator rootEval;
        Evaluator currentEval;
        this.tq.consumeWhitespace();
        String subQuery = this.consumeSubQuery();
        Evaluator newEval = QueryParser.parse(subQuery);
        boolean replaceRightMost = false;
        if (this.evals.size() == 1) {
            currentEval = this.evals.get(0);
            rootEval = currentEval;
            if (rootEval instanceof CombiningEvaluator.Or && combinator != ',') {
                currentEval = ((CombiningEvaluator.Or)currentEval).rightMostEvaluator();
                assert (currentEval != null);
                replaceRightMost = true;
            }
        } else {
            rootEval = currentEval = new CombiningEvaluator.And(this.evals);
        }
        this.evals.clear();
        switch (combinator) {
            case '>': {
                StructuralEvaluator.ImmediateParentRun run = currentEval instanceof StructuralEvaluator.ImmediateParentRun ? (StructuralEvaluator.ImmediateParentRun)currentEval : new StructuralEvaluator.ImmediateParentRun(currentEval);
                run.add(newEval);
                currentEval = run;
                break;
            }
            case ' ': {
                currentEval = new CombiningEvaluator.And(new Evaluator[]{new StructuralEvaluator.Parent(currentEval), newEval});
                break;
            }
            case '+': {
                currentEval = new CombiningEvaluator.And(new Evaluator[]{new StructuralEvaluator.ImmediatePreviousSibling(currentEval), newEval});
                break;
            }
            case '~': {
                currentEval = new CombiningEvaluator.And(new Evaluator[]{new StructuralEvaluator.PreviousSibling(currentEval), newEval});
                break;
            }
            case ',': {
                CombiningEvaluator.Or or;
                if (currentEval instanceof CombiningEvaluator.Or) {
                    or = (CombiningEvaluator.Or)currentEval;
                } else {
                    or = new CombiningEvaluator.Or();
                    or.add(currentEval);
                }
                or.add(newEval);
                currentEval = or;
                break;
            }
            default: {
                throw new Selector.SelectorParseException("Unknown combinator '%s'", new Object[]{Character.valueOf(combinator)});
            }
        }
        if (replaceRightMost) {
            ((CombiningEvaluator.Or)rootEval).replaceRightMostEvaluator(currentEval);
        } else {
            rootEval = currentEval;
        }
        this.evals.add(rootEval);
    }

    public static Evaluator parse(String query) {
        try {
            QueryParser p = new QueryParser(query);
            return p.parse();
        }
        catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage());
        }
    }

    public String toString() {
        return this.query;
    }

    Evaluator parse() {
        this.tq.consumeWhitespace();
        if (this.tq.matchesAny(Combinators)) {
            this.evals.add((Evaluator)new StructuralEvaluator.Root());
            this.combinator(this.tq.consume());
        } else {
            this.evals.add(this.consumeEvaluator());
        }
        while (true) {
            if (this.tq.isEmpty()) {
                if (this.evals.size() != 1) return new CombiningEvaluator.And(this.evals);
                return this.evals.get(0);
            }
            boolean seenWhite = this.tq.consumeWhitespace();
            if (this.tq.matchesAny(Combinators)) {
                this.combinator(this.tq.consume());
                continue;
            }
            if (seenWhite) {
                this.combinator(' ');
                continue;
            }
            this.evals.add(this.consumeEvaluator());
        }
    }

    private Evaluator has() {
        String subQuery = this.consumeParens();
        Validate.notEmpty((String)subQuery, (String)":has(selector) sub-select must not be empty");
        return new StructuralEvaluator.Has(QueryParser.parse(subQuery));
    }

    private Evaluator byClass() {
        String className = this.tq.consumeCssIdentifier();
        Validate.notEmpty((String)className);
        return new Evaluator.Class(className.trim());
    }

    private Evaluator is() {
        String subQuery = this.consumeParens();
        Validate.notEmpty((String)subQuery, (String)":is(selector) sub-select must not be empty");
        return new StructuralEvaluator.Is(QueryParser.parse(subQuery));
    }

    private Evaluator byTag() {
        Evaluator.Tag eval;
        String tagName = Normalizer.normalize((String)this.tq.consumeElementSelector());
        Validate.notEmpty((String)tagName);
        if (tagName.startsWith("*|")) {
            String plainTag = tagName.substring(2);
            eval = new CombiningEvaluator.Or(new Evaluator[]{new Evaluator.Tag(plainTag), new Evaluator.TagEndsWith(tagName.replace("*|", ":"))});
        } else {
            if (tagName.contains("|")) {
                tagName = tagName.replace("|", ":");
            }
            eval = new Evaluator.Tag(tagName);
        }
        return eval;
    }

    private Evaluator not() {
        String subQuery = this.consumeParens();
        Validate.notEmpty((String)subQuery, (String)":not(selector) subselect must not be empty");
        return new StructuralEvaluator.Not(QueryParser.parse(subQuery));
    }

    private Evaluator containsWholeText(boolean own) {
        String query = own ? ":containsWholeOwnText" : ":containsWholeText";
        String searchText = TokenQueue.unescape((String)this.consumeParens());
        Validate.notEmpty((String)searchText, (String)(query + "(text) query must not be empty"));
        return own ? new Evaluator.ContainsWholeOwnText(searchText) : new Evaluator.ContainsWholeText(searchText);
    }

    private Evaluator cssNthChild(boolean backwards, boolean ofType) {
        int b;
        int a;
        String arg = Normalizer.normalize((String)this.consumeParens());
        Matcher mAB = NTH_AB.matcher(arg);
        Matcher mB = NTH_B.matcher(arg);
        if ("odd".equals(arg)) {
            a = 2;
            b = 1;
        } else if ("even".equals(arg)) {
            a = 2;
            b = 0;
        } else if (mAB.matches()) {
            a = mAB.group(3) != null ? Integer.parseInt(mAB.group(1).replaceFirst("^\\+", "")) : 1;
            b = mAB.group(4) != null ? Integer.parseInt(mAB.group(4).replaceFirst("^\\+", "")) : 0;
        } else {
            if (!mB.matches()) {
                throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", new Object[]{arg});
            }
            a = 0;
            b = Integer.parseInt(mB.group().replaceFirst("^\\+", ""));
        }
        Object eval = ofType ? (backwards ? new Evaluator.IsNthLastOfType(a, b) : new Evaluator.IsNthOfType(a, b)) : (backwards ? new Evaluator.IsNthLastChild(a, b) : new Evaluator.IsNthChild(a, b));
        return eval;
    }

    private int consumeIndex() {
        String index = this.consumeParens().trim();
        Validate.isTrue((boolean)StringUtil.isNumeric((String)index), (String)"Index must be numeric");
        return Integer.parseInt(index);
    }

    static {
        Combinators = new char[]{',', '>', '+', '~', ' '};
        AttributeEvals = new String[]{"=", "!=", "^=", "$=", "*=", "~="};
        NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
        NTH_B = Pattern.compile("([+-])?(\\d+)");
    }

    private Evaluator matchesWholeText(boolean own) {
        String query = own ? ":matchesWholeOwnText" : ":matchesWholeText";
        String regex = this.consumeParens();
        Validate.notEmpty((String)regex, (String)(query + "(regex) query must not be empty"));
        return own ? new Evaluator.MatchesWholeOwnText(Pattern.compile(regex)) : new Evaluator.MatchesWholeText(Pattern.compile(regex));
    }

    private Evaluator byId() {
        String id = this.tq.consumeCssIdentifier();
        Validate.notEmpty((String)id);
        return new Evaluator.Id(id);
    }

    private String consumeSubQuery() {
        StringBuilder sq = StringUtil.borrowBuilder();
        boolean seenClause = false;
        while (!this.tq.isEmpty()) {
            if (this.tq.matchesAny(Combinators)) {
                if (seenClause) {
                    return StringUtil.releaseBuilder((StringBuilder)sq);
                }
                sq.append(this.tq.consume());
                continue;
            }
            seenClause = true;
            if (this.tq.matches("(")) {
                sq.append("(").append(this.tq.chompBalanced('(', ')')).append(")");
                continue;
            }
            if (this.tq.matches("[")) {
                sq.append("[").append(this.tq.chompBalanced('[', ']')).append("]");
                continue;
            }
            sq.append(this.tq.consume());
        }
        return StringUtil.releaseBuilder((StringBuilder)sq);
    }

    private Evaluator byAttribute() {
        Object eval;
        TokenQueue cq = new TokenQueue(this.tq.chompBalanced('[', ']'));
        String key = cq.consumeToAny(AttributeEvals);
        Validate.notEmpty((String)key);
        cq.consumeWhitespace();
        if (cq.isEmpty()) {
            eval = key.startsWith("^") ? new Evaluator.AttributeStarting(key.substring(1)) : (key.equals("*") ? new Evaluator.AttributeStarting("") : new Evaluator.Attribute(key));
        } else if (cq.matchChomp("=")) {
            eval = new Evaluator.AttributeWithValue(key, cq.remainder());
        } else if (cq.matchChomp("!=")) {
            eval = new Evaluator.AttributeWithValueNot(key, cq.remainder());
        } else if (cq.matchChomp("^=")) {
            eval = new Evaluator.AttributeWithValueStarting(key, cq.remainder());
        } else if (cq.matchChomp("$=")) {
            eval = new Evaluator.AttributeWithValueEnding(key, cq.remainder());
        } else if (cq.matchChomp("*=")) {
            eval = new Evaluator.AttributeWithValueContaining(key, cq.remainder());
        } else {
            if (!cq.matchChomp("~=")) {
                throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", new Object[]{this.query, cq.remainder()});
            }
            eval = new Evaluator.AttributeWithValueMatching(key, Pattern.compile(cq.remainder()));
        }
        return eval;
    }

    private QueryParser(String query) {
        Validate.notEmpty((String)query);
        this.query = query = query.trim();
        this.tq = new TokenQueue(query);
    }
}
