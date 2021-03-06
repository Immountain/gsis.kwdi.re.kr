(function (a) {
    a.widget("ui.multiselect", {
        _init: function () {
            this.element.hide();
            this.id = this.element.attr("id");
            this.container = a('<div class="ui-multiselect ui-helper-clearfix ui-widget"></div>').insertAfter(this.element);
            this.count = 0;
            this.selectedContainer = a('<div class="selected"></div>').appendTo(this.container);
            this.availableContainer = a('<div class="available"></div>').appendTo(this.container);
            this.selectedActions = a('<div class="actions ui-widget-header ui-helper-clearfix"><span class="count">0 ' + a.ui.multiselect.locale.itemsCount + '</span><a href="#" class="remove-all">' + a.ui.multiselect.locale.removeAll + "</a></div>").appendTo(this.selectedContainer);
            this.availableActions = a('<div class="actions ui-widget-header ui-helper-clearfix"><input type="text" class="search empty ui-widget-content ui-corner-all"/><a href="#" class="add-all">' + a.ui.multiselect.locale.addAll + "</a></div>").appendTo(this.availableContainer);
            this.selectedList = a('<ul class="selected connected-list"><li class="ui-helper-hidden-accessible"></li></ul>').bind("selectstart", function () {
                return false
            }).appendTo(this.selectedContainer);
            this.availableList = a('<ul class="available connected-list"><li class="ui-helper-hidden-accessible"></li></ul>').bind("selectstart", function () {
                return false
            }).appendTo(this.availableContainer);
            var b = this;
            this.container.width(this.element.width() + 1);
            this.selectedContainer.width(Math.floor(this.element.width() * this.options.dividerLocation) - 30);
            this.availableContainer.width(Math.floor(this.element.width() * (1 - this.options.dividerLocation)) + 30);
            this.selectedList.height(Math.max(this.element.height() - this.selectedActions.height(), 1));
            this.availableList.height(Math.max(this.element.height() - this.availableActions.height(), 1));
            if (!this.options.animated) {
                this.options.show = "show";
                this.options.hide = "hide"
            }
            this._populateLists(this.element.find("option"));
            if (this.options.sortable) {
                a("ul.selected").sortable({
                    placeholder: "ui-state-highlight", axis: "y", update: function (c, d) {
                        b.selectedList.find("li").each(function () {
                            if (a(this).data("optionLink")) {
                                a(this).data("optionLink").remove().appendTo(b.element)
                            }
                        })
                    }, receive: function (c, d) {
                        d.item.data("optionLink").attr("selected", true);
                        b.count += 1;
                        b._updateCount();
                        b.selectedList.children(".ui-draggable").each(function () {
                            a(this).removeClass("ui-draggable");
                            a(this).data("optionLink", d.item.data("optionLink"));
                            a(this).data("idx", d.item.data("idx"));
                            b._applyItemState(a(this), true)
                        });
                        setTimeout(function () {
                            d.item.remove()
                        }, 1)
                    }
                })
            }
            if (this.options.searchable) {
                this._registerSearchEvents(this.availableContainer.find("input.search"))
            } else {
                a(".search").hide()
            }
            a(".remove-all").click(function () {
                b._populateLists(b.element.find("option").removeAttr("selected"));
                return false
            });
            a(".add-all").click(function () {
                b._populateLists(b.element.find("option").attr("selected", "selected"));
                return false
            })
        }, destroy: function () {
            this.element.show();
            this.container.remove();
            a.widget.prototype.destroy.apply(this, arguments)
        }, _populateLists: function (c) {
            this.selectedList.children(".ui-element").remove();
            this.availableList.children(".ui-element").remove();
            this.count = 0;
            var d = this;
            var b = a(c.map(function (e) {
                var f = d._getOptionNode(this).appendTo(this.selected ? d.selectedList : d.availableList).show();
                if (this.selected) {
                    d.count += 1
                }
                d._applyItemState(f, this.selected);
                f.data("idx", e);
                return f[0]
            }));
            this._updateCount()
        }, _updateCount: function () {
            this.selectedContainer.find("span.count").text(this.count + " " + a.ui.multiselect.locale.itemsCount)
        }, _getOptionNode: function (b) {
            b = a(b);
            var c = a('<li class="ui-state-default ui-element" title="' + b.text() + '"><span class="ui-icon"/>' + b.text() + '<a href="#" class="action"><span class="ui-corner-all ui-icon"/></a></li>').hide();
            c.data("optionLink", b);
            return c
        }, _cloneWithData: function (c) {
            var b = c.clone();
            b.data("optionLink", c.data("optionLink"));
            b.data("idx", c.data("idx"));
            return b
        }, _setSelected: function (k, e) {
            k.data("optionLink").attr("selected", e);
            if (e) {
                var d = this._cloneWithData(k);
                k[this.options.hide](this.options.animated, function () {
                    a(this).remove()
                });
                d.appendTo(this.selectedList).hide()[this.options.show](this.options.animated);
                this._applyItemState(d, true);
                return d
            } else {
                var g = this.availableList.find("li"), b = this.options.nodeComparator;
                var h = null, f = k.data("idx"), j = b(k, a(g[f]));
                if (j) {
                    while (f >= 0 && f < g.length) {
                        j > 0 ? f++ : f--;
                        if (j != b(k, a(g[f]))) {
                            h = g[j > 0 ? f : f + 1];
                            break
                        }
                    }
                } else {
                    h = g[f]
                }
                var c = this._cloneWithData(k);
                h ? c.insertBefore(a(h)) : c.appendTo(this.availableList);
                k[this.options.hide](this.options.animated, function () {
                    a(this).remove()
                });
                c.hide()[this.options.show](this.options.animated);
                this._applyItemState(c, false);
                return c
            }
        }, _applyItemState: function (c, b) {
            if (b) {
                if (this.options.sortable) {
                    c.children("span").addClass("ui-icon-arrowthick-2-n-s").removeClass("ui-helper-hidden").addClass("ui-icon")
                } else {
                    c.children("span").removeClass("ui-icon-arrowthick-2-n-s").addClass("ui-helper-hidden").removeClass("ui-icon")
                }
                c.find("a.action span").addClass("ui-icon-minus").removeClass("ui-icon-plus");
                this._registerRemoveEvents(c.find("a.action"))
            } else {
                c.children("span").removeClass("ui-icon-arrowthick-2-n-s").addClass("ui-helper-hidden").removeClass("ui-icon");
                c.find("a.action span").addClass("ui-icon-plus").removeClass("ui-icon-minus");
                this._registerAddEvents(c.find("a.action"))
            }
            this._registerHoverEvents(c)
        }, _filter: function (f) {
            var c = a(this);
            var e = f.children("li"), b = e.map(function () {
                return a(this).text().toLowerCase()
            });
            var d = a.trim(c.val().toLowerCase()), g = [];
            if (!d) {
                e.show()
            } else {
                e.hide();
                b.each(function (h) {
                    if (this.indexOf(d) > -1) {
                        g.push(h)
                    }
                });
                a.each(g, function () {
                    a(e[this]).show()
                })
            }
        }, _registerHoverEvents: function (b) {
            b.removeClass("ui-state-hover");
            b.mouseover(function () {
                a(this).addClass("ui-state-hover")
            });
            b.mouseout(function () {
                a(this).removeClass("ui-state-hover")
            })
        }, _registerAddEvents: function (c) {
            var b = this;
            c.click(function () {
                var d = b._setSelected(a(this).parent(), true);
                b.count += 1;
                b._updateCount();
                return false
            }).each(function () {
                a(this).parent().draggable({
                    connectToSortable: "ul.selected", helper: function () {
                        var d = b._cloneWithData(a(this)).width(a(this).width() - 50);
                        d.width(a(this).width());
                        return d
                    }, appendTo: ".ui-multiselect", containment: ".ui-multiselect", revert: "invalid"
                })
            })
        }, _registerRemoveEvents: function (c) {
            var b = this;
            c.click(function () {
                b._setSelected(a(this).parent(), false);
                b.count -= 1;
                b._updateCount();
                return false
            })
        }, _registerSearchEvents: function (b) {
            var c = this;
            b.focus(function () {
                a(this).addClass("ui-state-active")
            }).blur(function () {
                a(this).removeClass("ui-state-active")
            }).keypress(function (d) {
                if (d.keyCode == 13) {
                    return false
                }
            }).keyup(function () {
                c._filter.apply(this, [c.availableList])
            })
        }
    });
    a.extend(a.ui.multiselect, {
        defaults: {
            sortable: true,
            searchable: true,
            animated: "fast",
            show: "slideDown",
            hide: "slideUp",
            dividerLocation: 0.6,
            nodeComparator: function (c, b) {
                var e = c.text(), d = b.text();
                return e == d ? 0 : (e < d ? -1 : 1)
            }
        }, locale: {addAll: "????????????", removeAll: "????????????", itemsCount: "?????? ?????????"}
    })
})(jQuery);